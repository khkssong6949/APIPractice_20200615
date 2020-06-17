package com.hgney.apipractice_20200615

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hgney.apipractice_20200615.datas.User
import com.hgney.apipractice_20200615.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivitiy() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {
    }
    override fun setValues() {
//        로그인한 사용자 정보를 서버에서 불러오기.
        ServerUtil.getRequestMainInfo(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                val data = json.getJSONObject("data")
                val topics = data.getJSONArray("topics")

                for (i in 0..topics.length()-1) {
                    val topicJson = topics.getJSONObject(i)
                }

            }
        })
    }
}