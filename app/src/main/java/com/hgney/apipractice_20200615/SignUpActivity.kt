package com.hgney.apipractice_20200615

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hgney.apipractice_20200615.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUpActivity : BaseActivitiy() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {

        emailCheckBtn.setOnClickListener {
//            입력한 이메일 받아오기

            val email = emilEdt.text.toString()

//            서버에 중복 확인
            ServerUtil.getRequestDuplicatedCheck(mContext, "EMAIL", email, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

                    val code = json.getInt("code")

                    runOnUiThread {
//                        UI처리 쓰레드에서 결과 확인 / 화면 반영
                        if (code == 200) {
                            emailCheckResultTxt.text = "사용해도 좋습니다."
                        }
                        else {
                            emailCheckResultTxt.text = "중복된 이메일이라 사용 불가합니다."
                        }
                    }


                }

            })

        }
    }

    override fun setValues() {
    }

}