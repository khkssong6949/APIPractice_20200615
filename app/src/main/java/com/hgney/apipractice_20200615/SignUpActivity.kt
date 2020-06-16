package com.hgney.apipractice_20200615

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hgney.apipractice_20200615.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_sign_up.*

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
            ServerUtil

        }
    }

    override fun setValues() {
    }

}