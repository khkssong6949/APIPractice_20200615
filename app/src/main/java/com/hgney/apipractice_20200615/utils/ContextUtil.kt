package com.hgney.apipractice_20200615.utils

import android.content.Context

class ContextUtil {
    companion object {

        //    메모장(preference) 파일 이름에 대응되는 개념 : 변수로 저장
        val prefName = "APIPracticef"

        //    저장할 항목들의 이름을 변수로 생성
        val USER_TOKEN = "USER_TOKEN"
//    항목에 데이터 저장(setter) or 불러오기(getter)

        //        메모장을 여는 느낌, Context.MODEPRIVATE은 내 앱어서만 보겠다는 고정적 문구, 스트링으로 저장
        fun setUserToken(context: Context, token: String) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putString(USER_TOKEN, token).apply()
        }

        //    스트링으로 빼낸다
        fun getUserToken(context: Context): String {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(USER_TOKEN, "")!!
        }

    }

}