package com.cherry.ai.ui.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.cherry.ai.R
import com.cherry.ai.ui.holder.CommonWordsContentHolder


/*
 * -----------------------------------------------------------------
 * Copyright (C) 2020-2080, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: CommonWordsAdapter
 * Author: Victor
 * Date: 2020/12/30 10:32
 * Description: 
 * -----------------------------------------------------------------
 */
class CommonWordsAdapter(context: Context, listener: AdapterView.OnItemClickListener) :
    BaseRecycleAdapter<String, RecyclerView.ViewHolder>(context, listener)  {
    override fun onCreateHeadVHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return null
    }

    override fun onBindHeadVHolder(viewHolder: RecyclerView.ViewHolder, data: String?, position: Int) {
    }

    override fun onCreateContentVHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CommonWordsContentHolder(inflate(R.layout.rv_common_words_cell ,parent))
    }

    override fun onBindContentVHolder(viewHolder: RecyclerView.ViewHolder, data: String?, position: Int) {
        val contentViewHolder = viewHolder as CommonWordsContentHolder

        contentViewHolder.bindData(data)

        contentViewHolder.mOnItemClickListener = listener
    }
}