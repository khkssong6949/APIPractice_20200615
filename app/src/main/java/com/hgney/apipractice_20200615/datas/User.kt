package com.hgney.apipractice_20200615.datas

import org.json.JSONObject

class User {

    companion object{
        fun getUserFromJson(json: JSONObject) : User{
            val u = User()
//          u에 들어갈 실제 데이터를 json에서 추출해서 대입
            u.id = json.getInt("id")
            u.email = json.getString("email")
            u.nickName = json.getString("nick_name")

            return u
        }
    }

    var id = 0
    var email = ""
    var nickName = ""

}