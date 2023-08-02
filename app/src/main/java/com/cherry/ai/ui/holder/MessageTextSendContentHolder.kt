package com.cherry.ai.ui.holder

import android.view.View
import com.cherry.ai.data.model.ChatMessage
import com.cherry.ai.util.TimeUtils
import kotlinx.android.synthetic.main.rv_message_text_send_cell.view.*


/*
 * -----------------------------------------------------------------
 * Copyright (C) 2020-2080, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: MessageTextSendContentHolder
 * Author: Victor
 * Date: 2020/12/30 10:34
 * Description: 
 * -----------------------------------------------------------------
 */
class MessageTextSendContentHolder(itemView: View) : ContentViewHolder(itemView) {

    fun bindData (data: ChatMessage?) {
        itemView.mTvMessage.setOnClickListener(this)

        TimeUtils.bindShowTime(itemView.mTvTime, data?.time)
        itemView.mTvMessage.text = data?.content

    }

    override fun onLongClick(v: View): Boolean {
        return false
    }


}