package com.hgney.apipractice_20200615.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.hgney.apipractice_20200615.R
import com.hgney.apipractice_20200615.datas.Topic

class TopicAdapter(context: Context, resId: Int, list: List<Topic>) : ArrayAdapter<Topic>(context, resId, list) {

    val mContext = context
    val mList = list
    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        //convertView를 임시row에 저장하고, null인지 확인.
        //=> 만약 null이라면, inf로 새로 XML을 그려서 tempRow를 채워줌.

        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.topic_list_item, null)
        }
        val row = tempRow!!

        return row
    }

}