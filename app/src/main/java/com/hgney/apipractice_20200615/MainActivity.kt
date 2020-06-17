package com.hgney.apipractice_20200615

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hgney.apipractice_20200615.adapters.TopicAdapter
import com.hgney.apipractice_20200615.datas.Topic
import com.hgney.apipractice_20200615.datas.User
import com.hgney.apipractice_20200615.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivitiy() {

    val topicList = ArrayList<Topic>()
    lateinit var topicAdapter: TopicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {
    }
    override fun setValues() {

        topicAdapter = TopicAdapter(mContext, R.layout.topic_list_item, topicList)
        topicListView.adapter = topicAdapter

//        로그인한 사용자 정보를 서버에서 불러오기.
        ServerUtil.getRequestMainInfo(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                val data = json.getJSONObject("data")
                val topics = data.getJSONArray("topics")

                for (i in 0..topics.length()-1) {
                    val topicJson = topics.getJSONObject(i)

                    //                    주제 하나에 대응되는 JSON을 넣어서 Topic객체로 얻어내자.
                    val topic = Topic.getTopicFromJson(topicJson)

//                    받아온 주제 목록을 리스트뷰의 재료로 추가
                    topicList.add(topic)
                }
//                리스트뷰의 내용 추가
                topicAdapter.notifyDataSetChanged()

            }
        })
    }
}