package com.cherry.ai.data.model

class ChatMessage {
    var content: String? = null
    var originalContent: String? = null
    var msgType: Int = 0
    var time: Long = 0
    var imageGenerationReq: ImageGenerationReq? = null
}
