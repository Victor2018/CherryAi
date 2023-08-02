package com.cherry.ai.ui.holder

import android.view.View
import com.cherry.ai.data.model.ChatMessage
import com.cherry.ai.util.ImageUtils
import com.cherry.ai.util.TimeUtils
import kotlinx.android.synthetic.main.rv_message_image_recv_cell.view.*

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2020-2080, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: MessageImageRecvContentHolder
 * Author: Victor
 * Date: 2020/12/30 10:34
 * Description: 
 * -----------------------------------------------------------------
 */
class MessageImageRecvContentHolder(itemView: View) : ContentViewHolder(itemView) {

    fun bindData (data: ChatMessage?) {
        TimeUtils.bindShowTime(itemView.mTvTime, data?.time)
        var url = data?.imageGenerationReq?.data?.firstOrNull()?.url
        ImageUtils.instance.loadImage(itemView.context,itemView.mIvMessage,url)

    }

    override fun onLongClick(v: View): Boolean {
        return false
    }

}