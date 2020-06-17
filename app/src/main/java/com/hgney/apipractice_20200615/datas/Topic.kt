package com.hgney.apipractice_20200615.datas

import org.json.JSONObject

class Topic {
    var id = 0
    var title = ""
    var imageUrl = ""

    companion object {
        fun getTopicFromJson(json : JSONObject) : Topic {
            val topic = Topic()


            return topic
        }
    }
}