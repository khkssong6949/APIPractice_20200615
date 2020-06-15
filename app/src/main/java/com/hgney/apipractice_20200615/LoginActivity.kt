package com.hgney.apipractice_20200615

import android.os.Bundle
import com.hgney.apipractice_20200615.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*

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

            ServerUtil.postRequestLogin(mContext, email, pw, null)


        }

    }

    override fun setValues() {
    }
}