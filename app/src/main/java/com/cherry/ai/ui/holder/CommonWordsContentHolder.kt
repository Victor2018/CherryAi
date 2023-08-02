package com.cherry.ai.ui.holder

import android.view.View
import kotlinx.android.synthetic.main.rv_common_words_cell.view.*

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2020-2080, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: CommonWordsContentHolder
 * Author: Victor
 * Date: 2020/12/30 10:34
 * Description: 
 * -----------------------------------------------------------------
 */
class CommonWordsContentHolder(itemView: View) : ContentViewHolder(itemView) {

    fun bindData (data: String?) {
        itemView.mTvCommonWords.text = data
    }

    override fun onLongClick(v: View): Boolean {
        return false
    }

}