package com.hgney.apipractice_20200615.datas

import org.json.JSONObject

class Topic {
    var id = 0
    var title = ""
    var imageUrl = ""

    val sideList = ArrayList<TopicSide>()

    companion object {
        fun getTopicFromJson(json : JSONObject) : Topic {
            val topic = Topic()

            topic.id = json.getInt("id")
            topic.title = json.getString("title")
            topic.imageUrl = json.getString("img_url")

//            선택 가능 진영 정보 파싱 => JSONArray 파싱부터
            val sides = json.getJSONArray("sides")
            for (i in 0..sides.length()-1){
                val side = sides.getJSONObject(i)

//                json => TopicSide 객체로 변환.
//                어떻게 해야 주제의 하위항목이 되나? => 해당 주제의 진영 배열의 재료로 추가.
                val topicSide = TopicSide.getTopicSideFromJson(side)
                topic.sideList.add(topicSide)
            }

            return topic
        }
    }

}