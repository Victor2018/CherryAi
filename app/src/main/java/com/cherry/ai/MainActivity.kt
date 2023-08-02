package com.cherry.ai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.cherry.ai.data.model.*
import com.cherry.ai.data.repository.OpenApiRepository
import com.cherry.ai.ui.adapter.MessageAdapter
import com.cherry.ai.ui.vm.OpenApiViewModel
import com.cherry.ai.util.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.rv_message_text_recv_cell.view.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.victor.http.lib.adapter.NetworkResponse
import org.victor.http.lib.data.HttpResult

class MainActivity : AppCompatActivity(),OnClickListener,OnTouchListener,OnItemClickListener,
    TextWatcher {

    private val openApiVM by viewModels<OpenApiVM> {
        InjectorUtils.provideOpenApiVM(this)
    }
    private lateinit var openApiViewModel: OpenApiViewModel

    var mMessageAdapter: MessageAdapter? = null
    var lastContent: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StatusBarUtil.translucentStatusBar(this, true,true,false)
        initialize()
    }

    fun initialize () {
        openApiViewModel = InjectorUtils.provideOpenApiViewModel(this)

        subscribeUi()

        mMessageAdapter = MessageAdapter(this,this)
        mRvMessage.adapter = mMessageAdapter

        setWindowSoftInput(
            float = ll_bottom,
            setPadding = true,
            onChanged = {
                Log.d("SoftInput", "visibility = ${hasSoftInput()}")
            }
        )

        mIvBack.setOnClickListener(this)
        mRvMessage.setOnClickListener(this)
        mIvCommonWords.setOnClickListener(this)
        mIvSend.setOnClickListener(this)

        mRvMessage.setOnTouchListener(this)
        mEtMessage.addTextChangedListener(this)
    }

    fun subscribeUi() {
        openApiVM.completionData.observe(this, Observer {
            when(it) {
                is HttpResult.Success -> {
                    var content = it.value.choices?.firstOrNull()?.message?.content
                    updateLastTextMessage(MessageType.TYPE_RECV_TEXT,content)
                }
                is HttpResult.Error -> {
                    updateLastTextMessage(MessageType.TYPE_NOTIFY,it.message)
                }
            }
        })

        lifecycleScope.launch {
            openApiViewModel.completionFlowData.collect {
                when(it) {
                    is HttpResult.Success -> {
                        var content = it.value.choices?.firstOrNull()?.message?.content
                        updateLastTextMessage(MessageType.TYPE_RECV_TEXT,content)
                    }
                    is HttpResult.Error -> {
                        updateLastTextMessage(MessageType.TYPE_NOTIFY,it.message)
                    }
                }
            }
        }


        openApiVM.generateImageData.observe(this, Observer {
            when(it) {
                is HttpResult.Success -> {
                    updateLastImageMessage(MessageType.TYPE_RECV_IMAGE,it.value,null)
                }
                is HttpResult.Error -> {
                    updateLastImageMessage(MessageType.TYPE_NOTIFY,null,it.message)
                }
            }
        })
    }

    private fun sendCompletionRequest (content: String?) {
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this,"please enter message",Toast.LENGTH_SHORT).show()
            return
        }

        val body = CompletionParm()
        val role = CompletionRole()
        role.content = content
        body.messages = arrayListOf(role)

//        openApiVM.fetchCompletion(body)
        openApiViewModel.fetchCompletion(body)
    }

    private fun sendGenerateImageRequest (content: String?) {
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this,"please enter message",Toast.LENGTH_SHORT).show()
            return
        }

        val body = ImageGenerationParm()
        body.prompt = content

        openApiVM.generateImage(body)
    }

    fun addSendTextMessage () {
        var content = mEtMessage.text.toString()

        val msg = ChatMessage()
        msg.msgType = MessageType.TYPE_SEND_TEXT
        msg.content = content
        msg.time = System.currentTimeMillis()

        var count = mMessageAdapter?.getContentItemCount() ?: 0
        mMessageAdapter?.add(msg)
        mMessageAdapter?.notifyItemInserted(count)

        mRvMessage.smoothScroll2LastPosition()

        mPbThinking.visibility = View.VISIBLE
        mIvSend.visibility = View.GONE
    }

    fun addThinkingTextMessage () {
        val msg = ChatMessage()
        msg.msgType = MessageType.TYPE_GPT_THINKING
        msg.time = System.currentTimeMillis()

        var count = mMessageAdapter?.getContentItemCount() ?: 0
        mMessageAdapter?.add(msg)
        mMessageAdapter?.notifyItemInserted(count)

        mRvMessage.smoothScroll2LastPosition()
    }

    fun updateLastTextMessage(msgType: Int,content: String?) {
        var count = mMessageAdapter?.getContentItemCount() ?: 0
        val msg = mMessageAdapter?.getItem(count - 1)
        msg?.msgType = msgType
        msg?.content = content
        msg?.originalContent = lastContent
        msg?.time = System.currentTimeMillis()

        mMessageAdapter?.notifyItemChanged(count)

        mRvMessage.smoothScroll2LastPosition()

        mPbThinking.visibility = View.GONE
        mIvSend.visibility = View.VISIBLE
    }

    fun updateLastImageMessage(msgType: Int, data: ImageGenerationReq?, error: String?) {
        var count = mMessageAdapter?.getContentItemCount() ?: 0
        val msg = mMessageAdapter?.getItem(count - 1)
        msg?.msgType = msgType
        msg?.imageGenerationReq = data
        msg?.content = error
        msg?.originalContent = lastContent
        msg?.time = System.currentTimeMillis()

        mMessageAdapter?.notifyItemChanged(count)

        mRvMessage.smoothScroll2LastPosition()

        if (msgType == MessageType.TYPE_GPT_THINKING) {
            mPbThinking.visibility = View.VISIBLE
            mIvSend.visibility = View.GONE
        } else {
            mPbThinking.visibility = View.GONE
            mIvSend.visibility = View.VISIBLE
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mIvBack -> {
                finish()
            }
            R.id.mIvCommonWords -> {
                if (hasSoftInput()) {
                    mFlBottomPanel.visibility = VISIBLE
                } else {
                    if (mFlBottomPanel.visibility == VISIBLE) {
                        mFlBottomPanel.visibility = GONE
                    } else {
                        mFlBottomPanel.visibility = VISIBLE
                    }
                }
                hideSoftInput() // 隐藏键盘
            }
            R.id.mIvSend -> {
                var content = mEtMessage.text.toString()
                if (TextUtils.isEmpty(content)) {
                    var speaking = mIvSend.tag?.toString()?.toBoolean() ?: false
                    if (speaking) {
                        mIvSend.setImageResource(R.mipmap.ic_voice)
                    } else {
                        mIvSend.setImageResource(R.mipmap.ic_stop)
                    }
                    mIvSend.tag = !speaking
                    return
                }
                sendMessage(false)
                mFlBottomPanel.visibility = GONE
            }
            R.id.mRvMessage -> {
                hideSoftInput()
            }
        }
    }

    fun sendMessage(retry: Boolean) {
        var content = mEtMessage.text.toString()
        if (retry) {
            if (mToggleImage.isChecked) {
                updateLastImageMessage(MessageType.TYPE_GPT_THINKING,null,null)
            } else {
                updateLastTextMessage(MessageType.TYPE_GPT_THINKING,null)
            }
            content = lastContent ?: ""
        } else {
            addSendTextMessage()
            addThinkingTextMessage()
            lastContent = mEtMessage.text.toString()
        }
        if (mToggleImage.isChecked) {
            sendGenerateImageRequest(content)
        } else {
            sendCompletionRequest(content)
        }

        mEtMessage.setText("")
    }

    override fun onItemClick(p0: AdapterView<*>?, v: View?, position: Int, id: Long) {
        when (v?.id) {
            R.id.mTvMessage -> {
                var textView = v?.mTvMessage
                ClipboardUtil.copy(this,Constant.COPY_MESSAGE_KEY,textView?.text)
                ToastUtils.showShort("已复制到系统剪贴板")
            }
            R.id.mTvRetry -> {
                sendMessage(true)
            }
        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        v?.clearFocus() // 清除文字选中状态
        hideSoftInput() // 隐藏键盘
        mFlBottomPanel.visibility = GONE
        return false
    }

    override fun afterTextChanged(s: Editable?) {
        var length = s?.length ?: 0
        if (length > 0) {
            mIvSend.setImageResource(R.mipmap.ic_send)
        } else {
            mIvSend.setImageResource(R.mipmap.ic_voice)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    fun setCommonWordsText(commonWorks: String?,generateImage: Boolean) {
        mEtMessage.setText(commonWorks)
        mToggleImage.isChecked = generateImage
    }
}