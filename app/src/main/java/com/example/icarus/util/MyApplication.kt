package com.example.icarus.util

import android.app.Application
import com.example.icarus.dto.DBHelper

class MyApplication : Application() {
    companion object {
        lateinit var db: DBHelper
        fun dataReset(){//데이터 초기화 하는 함수
            db.drop()//db 삭제
        }
    }

    override fun onCreate() {
        db = DBHelper(this)
        super.onCreate()
    }
}