package com.cherry.ai.ui.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.cherry.ai.R
import com.cherry.ai.data.model.ChatMessage
import com.cherry.ai.data.model.MessageType
import com.cherry.ai.ui.holder.*

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: MessageAdapter
 * Author: Victor
 * Date: 2023/07/14 18:24
 * Description: 
 * -----------------------------------------------------------------
 */

class MessageAdapter(context: Context, listener: AdapterView.OnItemClickListener) :
    BaseRecycleAdapter<ChatMessage, RecyclerView.ViewHolder>(context, listener) {

    override fun onCreateHeadVHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return null
    }

    override fun onBindHeadVHolder(viewHolder: RecyclerView.ViewHolder, data: ChatMessage?, position: Int) {
    }

    override fun onCreateContentVHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            MessageType.TYPE_GPT_THINKING -> {
                return MessageGptThinkingContentHolder(inflate(R.layout.rv_message_gpt_thinking_cell, parent))
            }
            MessageType.TYPE_SEND_TEXT -> {
                return MessageTextSendContentHolder(inflate(R.layout.rv_message_text_send_cell, parent))
            }
            MessageType.TYPE_RECV_TEXT -> {
                return MessageTextRecvContentHolder(inflate(R.layout.rv_message_text_recv_cell, parent))
            }
            MessageType.TYPE_RECV_IMAGE -> {
                return MessageImageRecvContentHolder(inflate(R.layout.rv_message_image_recv_cell, parent))
            }
            MessageType.TYPE_NOTIFY -> {
                return MessageNotifyContentHolder(inflate(R.layout.rv_message_notify_cell, parent))
            }
        }
        return MessageNotifyContentHolder(inflate(R.layout.rv_message_notify_cell, parent))
    }

    override fun onBindContentVHolder(viewHolder: RecyclerView.ViewHolder, data: ChatMessage?, position: Int) {
        if (viewHolder is MessageGptThinkingContentHolder) {
            viewHolder.mOnItemClickListener = listener
            viewHolder.bindData(data)
        } else if (viewHolder is MessageTextSendContentHolder) {
            viewHolder.mOnItemClickListener = listener
            viewHolder.bindData(data)
        } else if (viewHolder is MessageTextRecvContentHolder) {
            viewHolder.mOnItemClickListener = listener
            viewHolder.bindData(data)
        } else if (viewHolder is MessageImageRecvContentHolder) {
            viewHolder.mOnItemClickListener = listener
            viewHolder.bindData(data)
        } else if (viewHolder is MessageNotifyContentHolder) {
            viewHolder.mOnItemClickListener = listener
            viewHolder.bindData(data)
        }
    }


    override fun getItemViewType(position: Int): Int {
        var viewType = super.getItemViewType(position)
        if (viewType == ITEM_TYPE_CONTENT) {
            val data = getItem(position)
            viewType = data?.msgType ?: MessageType.TYPE_NOTIFY
        }
        return viewType
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        if (holder is MessageTextRecvContentHolder) {
            holder.startFadeInAnimation()
        }
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        if (holder is MessageTextRecvContentHolder) {
            holder.stopFadeInAnimation()
        }
    }

}