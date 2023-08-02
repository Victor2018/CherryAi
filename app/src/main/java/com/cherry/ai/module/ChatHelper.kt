package com.cherry.ai.module

import androidx.recyclerview.widget.RecyclerView
import com.cherry.ai.data.model.ChatMessage
import com.cherry.ai.data.model.MessageType

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: ChatHelper
 * Author: Victor
 * Date: 2023/07/21 10:13
 * Description: 
 * -----------------------------------------------------------------
 */

class ChatHelper {

    private object Holder { val instance = ChatHelper() }

    companion object {
        val instance: ChatHelper by lazy { Holder.instance }
    }

    var mRvMessage: RecyclerView? = null

    fun init(rv: RecyclerView?) {
        mRvMessage = rv
    }

    fun obtainSendMessage (content: String): ChatMessage {
        val msg = ChatMessage()
        msg.msgType = MessageType.TYPE_SEND_TEXT
        msg.content = content
        msg.time = System.currentTimeMillis()
        return msg
    }
}