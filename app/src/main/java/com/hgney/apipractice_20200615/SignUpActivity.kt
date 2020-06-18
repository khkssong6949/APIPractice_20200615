package com.hgney.apipractice_20200615

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.hgney.apipractice_20200615.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUpActivity : BaseActivitiy() {
//    이메일 검사 결과 저장용 변수. => 기본값은 통과 실패로 (false) 기록.
    var isEmailOk = false
    var isNickNameOk = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {

        //        이메일 입력값이 변경되면 => 무조건 다시 검사를 받으라고 문구 / Boolean 변경

        emailEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            //            문구가 바꼈을때
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

//                이메일 검사 결과를 => 검사 필요 문구로.
                emailCheckResultTxt.text = "이메일 중복검사를 해주세요."
//                  이메일 중복검사를 다시 해야하므로 => 사용 불가로 처리
                isEmailOk = false

            }

        })

//          닉네임 검사 결과도, 입력값이 변경되면 재검사 요구.
        nickNameEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                nickNameCheckResultTxt.text = "닉네임 중복검사를 해주세요."
                isNickNameOk = false
            }

        })





        emailCheckBtn.setOnClickListener {
//            입력한 이메일 받아오기

            val email = emailEdt.text.toString()

//            서버에 중복 확인
            ServerUtil.getRequestDuplicatedCheck(mContext, "EMAIL", email, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

                    val code = json.getInt("code")

                    runOnUiThread {
//                        UI처리 쓰레드에서 결과 확인 / 화면 반영
                        if (code == 200) {
                            emailCheckResultTxt.text = "사용해도 좋습니다."
                            isEmailOk = true
                        }
                        else {
                            emailCheckResultTxt.text = "중복된 이메일이라 사용 불가합니다."
                        }
                    }
                }
            })
        }

        nickCheckBtn.setOnClickListener {
//            입력한 닉네임 가져오기
            val nickname = nickNameEdt.text.toString()

//            서버에서 중복 확인
            ServerUtil.getRequestDuplicatedCheck(mContext, "NICK_NAME", nickname, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {
                    val code = json.getInt("code")

                    runOnUiThread {
                        if(code ==200){
                            nickNameCheckResultTxt.text = "사용해도 좋습니다."
                            isNickNameOk = true
                        }
                        else{
                            nickNameCheckResultTxt.text ="중복된 닉네임이라 사용 불가합니다."
                        }
                    }
                }

            })

        }

        signUpBtn.setOnClickListener {

            if(!isEmailOk){
                Toast.makeText(mContext, "이메일 중복검사를 통과해야 합니다.", Toast.LENGTH_SHORT).show()
//                return : 함수의 수행결과를 지정하는 문구
//                리턴타입X의 리턴 : 함수를 강제종료시키는 의미로 주로 사용
//                @라벨 => 어디를 종료시킬지 명시
                return@setOnClickListener
            }
            if(!isNickNameOk){
                Toast.makeText(mContext, "닉네임 중복검사를 통과해야 합니다.", Toast.LENGTH_SHORT).show()
//                return : 함수의 수행결과를 지정하는 문구
//                리턴타입X의 리턴 : 함수를 강제종료시키는 의미로 주로 사용
//                @라벨 => 어디를 종료시킬지 명시
                return@setOnClickListener
            }

            val inputPassword = passwordEdt.text.toString()

            if (inputPassword.length < 8) {
                Toast.makeText(mContext, "비밀번호는 8글자 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val inputEmail = emailEdt.text.toString()
            val inputNickName = nickNameEdt.text.toString()


//            서버에 회원가입 요청
            ServerUtil.putRequestSignUp(mContext, inputEmail, inputPassword, inputNickName, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

                    val code = json.getInt("code")

                    if (code == 200) {
                        runOnUiThread {
                            Toast.makeText(mContext, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }

                }

            })




        }


    }

    override fun setValues() {
    }

}