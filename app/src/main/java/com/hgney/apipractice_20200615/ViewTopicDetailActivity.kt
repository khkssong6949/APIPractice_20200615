package com.hgney.apipractice_20200615

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.hgney.apipractice_20200615.datas.Topic
import com.hgney.apipractice_20200615.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_topic_detail.*
import org.json.JSONObject

class ViewTopicDetailActivity : BaseActivitiy() {

//    다른화면에서 보내주는 주제 id값 저장 변수
    //    -1 ? 정상적인 id는 절대 -1일 수 없다.
//    만약 이 값이 계속 -1이라면 => 잘못 받아왔거나 등의 예외처리.
    var mTopicId = -1

    //    서버에서 받아온 주제 정보를 저장할 멤버변수
    lateinit var mTopic : Topic


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_topic_detail)
        setupEvents()
        setValues()
    }


    override fun setupEvents() {

        voteToFirstBtn.setOnClickListener {

            ServerUtil.postRequestVote(mContext, mTopic.sideList[0].id, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

                }
            })
        }

        voteToSecondBtn.setOnClickListener {
            ServerUtil.postRequestVote(mContext, mTopic.sideList[1].id, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

                }
            })
        }

    }



    override fun setValues() {

        mTopicId = intent.getIntExtra("topic_id", -1)

        if (mTopicId == -1) {
            Toast.makeText(mContext, "잘못된 접근입니다.", Toast.LENGTH_SHORT).show()
//            추가 진행을 막자
            return
        }

//       제대로 id값을 받아온 경우 => 서버에 해당 토픽 진행상황 조회
        getTopicDetailFromServer()
    }

    //    진행상황을 받아와주는 함수 (별도 기능)
    fun getTopicDetailFromServer() {

        ServerUtil.getRequestTopicDetail(mContext, mTopicId, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                val data = json.getJSONObject("data")
                val topic = data.getJSONObject("topic")

                val topicObj = Topic.getTopicFromJson(topic)
                mTopic = topicObj

                runOnUiThread {
                    topicTitleTxt.text = mTopic.title
                    Glide.with(mContext).load(mTopic.imageUrl).into(topicImg)

                    firstSideTitle.text = mTopic.sideList[0].title
                    secondSideTitle.text = mTopic.sideList[1].title

                    firstSideVoteCountTxt.text = "${mTopic.sideList[0].voteCount}표"
                    secondSideVoteCountTxt.text = "${mTopic.sideList[1].voteCount}표"
                }

            }

        })

    }

}