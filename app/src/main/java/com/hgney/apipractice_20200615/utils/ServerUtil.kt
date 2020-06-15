package com.hgney.apipractice_20200615.utils

import android.content.Context
import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

//    Java=> static 에 대응되는 개념
//    어느 객체가 하던 상관X, 기능만 잘 되면 그만인 것들을 모아두는 영역

    companion object{
//        호스트가 어디인지 명시 => 가져다가 사용
        val BASE_URL ="http://15.165.177.142"

//        로그인 기능을 포스트로 요청하는 함수+화면에서 받아야할 데이터를 매개변수로 넣는다.
        fun postRequestLogin(context: Context, email:String, pw:String,  handler: JsonResponseHandler?){

//          클라이언트로 동작해주는 변수
            val client = OkHttpClient()

//    어느 기능 주소로 가는지 호스트와 조합해서 명시
            val urlString = "${BASE_URL}/user"

//    서버에 전달할 데이터를 담는 과정(포스트 - 폼데이터)
            val formData = FormBody.Builder()
                .add("email", email)
                .add("password",pw)
                .build()

//    서버에 요청할 모든 정보를 담는 request 변수 생성
            val request = Request.Builder()
                .url(urlString)
                .post(formData)
//                .header() // api에서 헤더를 요구하면 여기서 첨부
                .build()

            client.newCall(request).enqueue(object : Callback{



                override fun onFailure(call: Call, e: IOException) {
//                    연결 자체에 실패한 경우
                }

                override fun onResponse(call: Call, response: Response) {
//                    서버 연결 성공 => 어떤 내용이던 응답은 받은 경우
//                    서버의 응답중 본문을 String으로 저장
                    val bodyString = response.body!!.string()

//                    본문 String을 => JSON형태로 변환 => 변수에 저장
                    val json = JSONObject(bodyString)
                    Log.d("JSON응답", json.toString())
                }


            })
        }

    }


//    서버 통신의 응답내용을 Activity에 전달해주는 인터페이스
    interface JsonResponseHandler {
        fun onResponse(json : JSONObject)
    }

}