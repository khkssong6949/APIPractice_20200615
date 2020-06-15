package com.hgney.apipractice_20200615

import android.os.Bundle
import android.widget.Toast
import com.hgney.apipractice_20200615.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : BaseActivitiy() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {

        loginBtn.setOnClickListener {
            val email = emailEdt.text.toString()
            val pw = passwordEdt.text.toString()

//            입력한 ID / PW가 진짜 회원인지 서버에 물어봐야함 (요청 필요)

            ServerUtil.postRequestLogin(mContext, email, pw, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
//                    서버의 응답을 처리하는 코드들

//                    제일 큰 껍데기 json 변수에서 => Int 추출
//                    어떤 Int? => code라는 이름이 붙어있는 Int
                    val codeNumber = json.getInt("code")

                    if (codeNumber == 200) {
//                        로그인 성공
                    } else {
//                        로그인 실패
//                        UI반영 : 토스트로 "로그인 실패" 출력
//                         인터넷 => 백그라운드 쓰레드 => UI 접근 => 강제종료
//                        UI쓰레드가 => 토스트를 띄우도록

                        runOnUiThread {
                            Toast.makeText(mContext, "로그인 실패", Toast.LENGTH_SHORT).show()
                        }

                    }


                }

            })
        }

    }

    override fun setValues() {
    }
}