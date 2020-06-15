package com.hgney.apipractice_20200615

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivitiy : AppCompatActivity() {

    val mContext =this

    abstract fun setupEvents()
    abstract fun setValues()


}