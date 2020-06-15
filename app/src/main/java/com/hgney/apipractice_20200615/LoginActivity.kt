package com.hgney.apipractice_20200615

import android.os.Bundle

class LoginActivity : BaseActivitiy() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {
    }

    override fun setValues() {
    }
}