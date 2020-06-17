package com.hgney.apipractice_20200615.datas

import org.json.JSONObject

class Topic {
    var id = 0
    var title = ""
    var imageUrl = ""

    companion object {
        fun getTopicFromJson(json : JSONObject) : Topic {
            val topic = Topic()

            topic.id = json.getInt("id")
            topic.title = json.getString("title")
            topic.imageUrl = json.getString("img_url")

            return topic
        }
    }

}