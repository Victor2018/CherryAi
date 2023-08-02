package com.cherry.ai.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.cherry.ai.MainActivity
import com.cherry.ai.R
import com.cherry.ai.ui.BaseFragment
import com.cherry.ai.ui.adapter.CommonWordsAdapter
import com.cherry.ai.util.Constant
import com.cherry.ai.util.ResUtils
import kotlinx.android.synthetic.main.frag_common_words.*


/*
 * -----------------------------------------------------------------
 * Copyright (C) 2020-2080, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: CommonWordsFragment
 * Author: Victor
 * Date: 2021/7/23 17:58
 * Description: 
 * -----------------------------------------------------------------
 */
class CommonWordsFragment: BaseFragment(),AdapterView.OnItemClickListener {
    companion object {

        fun newInstance(isEmployer: Boolean): CommonWordsFragment {
            return newInstance(0,isEmployer)
        }
        fun newInstance(id: Int,isEmployer: Boolean): CommonWordsFragment {
            val fragment = CommonWordsFragment()
            val bundle = Bundle()
            bundle.putInt(ID_KEY, id)
            bundle.putBoolean(Constant.INTENT_DATA_KEY, isEmployer)
            fragment.arguments = bundle
            return fragment
        }
    }

    var mCommonWordsAdapter: CommonWordsAdapter? = null

    override fun getLayoutResource() = R.layout.frag_common_words

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialize()
        initData()
    }

    fun initialize () {
        mCommonWordsAdapter = CommonWordsAdapter(requireContext(),this)
        mRvCommonWords.adapter = mCommonWordsAdapter
    }

    fun initData () {
        var commonWords = ResUtils.getStringArrayRes(R.array.message_common_words)
        mCommonWordsAdapter?.clear()
        mCommonWordsAdapter?.add(commonWords?.toList())
        mCommonWordsAdapter?.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        mRvCommonWords.requestFocus()
        mRvCommonWords.requestFocusFromTouch()
    }
    override fun handleBackEvent(): Boolean {
        return false
    }

    override fun freshFragData() {
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var commonWord = mCommonWordsAdapter?.getItem(position)
//        LiveDataBus.send(IMActions.SEND_COMMON_WORDS_MESSAGE,commonWord)

        var parentAct = activity as MainActivity
        var count = mCommonWordsAdapter?.getContentItemCount() ?: 0
        var generateImage = position == count - 1
        parentAct.setCommonWordsText(commonWord,generateImage)
    }
}