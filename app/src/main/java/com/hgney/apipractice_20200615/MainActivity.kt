package com.hgney.apipractice_20200615

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hgney.apipractice_20200615.adapters.TopicAdapter
import com.hgney.apipractice_20200615.datas.Topic
import com.hgney.apipractice_20200615.datas.User
import com.hgney.apipractice_20200615.utils.ContextUtil
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

        //        응용문제.
//        로그아웃 버튼이 눌리면 => 정말 로그아웃할건지 확인을 받자.
//        확인버튼을 누르면 실제 로그아웃 처리 진행.
//        저장된  토큰을 다시 빈칸으로 돌려주자. (로그아웃)
//        메인액티비티 종료 => 로그인화면으로 이동

        logoutBtn.setOnClickListener {

            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("로그아웃")
            alert.setMessage("정말 로그아웃 하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->

//                저장 안되있을때의 기본값으로 세팅 (없던것과 동일하게 처리)
                ContextUtil.setUserToken(mContext, "")

                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)

                finish()

            })
            alert.setNegativeButton("취소", null)
            alert.show()

        }


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
                runOnUiThread {
                    topicAdapter.notifyDataSetChanged()
                }

            }
        })
    }
}