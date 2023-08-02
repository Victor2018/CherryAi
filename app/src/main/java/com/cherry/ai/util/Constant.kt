package com.cherry.ai.util

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: Constant
 * Author: Victor
 * Date: 2022/3/1 18:28
 * Description: 
 * -----------------------------------------------------------------
 */

object Constant {
    const val INTENT_DATA_KEY                   = "INTENT_DATA_KEY"
    const val IS_ONLINE_KEY                     = "IS_ONLINE_KEY"
    const val SHOPPING_CART_PAY_KEY             = "SHOPPING_CART_PAY_KEY"
    const val STATUS_KEY                        = "STATUS_KEY"
    const val ID_KEY                            = "ID_KEY"
    const val POSITION_KEY                      = "POSITION_KEY"
    const val ORDER_NO_KEY                      = "ORDER_NO_KEY"
    const val ERROR_MSG_KEY                     = "ERROR_MSG_KEY"
    const val IM_MESSAGE_KEY                    = "IM_MESSAGE_KEY"
    const val VIDEO_POSTER_KEY                  = "VIDEO_POSTER_KEY"
    const val COPY_LINK_KEY                     = "COPY_LINK_KEY"
    const val COPY_MESSAGE_KEY                  = "COPY_MESSAGE_KEY"
    const val VIDEO_MUTE_KEY                    = "VIDEO_MUTE_KEY"
    const val AUDIO_MUTE_KEY                    = "AUDIO_MUTE_KEY"
    const val ONLINE_FLAG_KEY                   = "ONLINE_FLAG_KEY"
    const val GOODS_TYPE_KEY                    = "GOODS_TYPE_KEY"
    const val GOODS_ID_KEY                      = "GOODS_ID_KEY"
    const val COPY_PROMOTE_CODE_KEY             = "COPY_PROMOTE_CODE_KEY"
    const val SUB_ORDER_ID_KEY                  = "SUB_ORDER_ID_KEY"

    const val MA_DATA                           = "madata"
    const val SHARE_TYPE                        = "text/plain"
    const val MAIL_TO                           = "mailto:%s"

    object Msg {
        const val HIDE_PLAY_TOP_BOTTOM_CTRL_VIEW              = 0x1001
        const val HIDE_PLAY_RIGHT_CTRL_VIEW                   = 0x1002
        const val WXPAY_RESULT                                = 0x1003
        const val WX_AUTH_ERROR                               = 0x1004
        const val LOGIN_AUTH_WEIXIN                           = 0x1005
        const val SHARE_WECHAT_RESULT                         = 0x1006
        const val UPGRADE_APP                                 = 0x1007
    }
    object Action {
        const val COLLAPSE_INPUT_PANEL          = 0x2001
        const val LV_SCROLL_BOTTOM              = 0x2002
        const val GO_MAIN                       = 0x2003
    }

}