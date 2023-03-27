package com.example.icarus.dto

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.view.View
import android.widget.Toast
import java.io.File

class DBHelper(
    private val context: Context?,
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "ICARUS"

        // Table
        const val TABLE_NAME = "MEMBERS"
        const val UID = "UID"
        const val COL_STUDENT_ID = "STUDENT_ID"
        const val COL_NAME = "NAME"
        const val COL_GENDER = "GENDER"
        const val COL_PHONE_NUMBER = "PHONE_NUMBER"
        const val COL_GRADE = "GRADE"
        const val COL_MAJOR = "MAJOR"
        const val COL_TEAM = "TEAM"
    }
    override fun onCreate(db: SQLiteDatabase) {
        var sql: String = "CREATE TABLE if not exists " +
                "$TABLE_NAME ($UID integer primary key autoincrement, " +
                "$COL_STUDENT_ID text, $COL_NAME text, $COL_GENDER text, $COL_PHONE_NUMBER text, $COL_GRADE text, $COL_MAJOR text, $COL_TEAM text);"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql: String = "DROP TABLE if exists $TABLE_NAME"
        db.execSQL(sql)
        onCreate(db)
    }

    val allMembers:MutableList<Member>
        @SuppressLint("Range")
        get() {
            val members = ArrayList<Member>()
            val selectQueryHandler = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQueryHandler,null)
            if(cursor.moveToFirst()){
                do{
                    val member = Member(
                        cursor.getInt(cursor.getColumnIndex(UID)),
                        cursor.getString(cursor.getColumnIndex(COL_STUDENT_ID)),
                        cursor.getString(cursor.getColumnIndex(COL_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_GENDER)),
                        cursor.getString(cursor.getColumnIndex(COL_PHONE_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(COL_GRADE)),
                        cursor.getString(cursor.getColumnIndex(COL_MAJOR)),
                        cursor.getString(cursor.getColumnIndex(COL_TEAM))
                    )
                    members.add(member)
                }while(cursor.moveToNext())
            }
            db.close()
            return members
        }
    fun addMember(member: Member){
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(COL_NAME, member.name)
        values.put(COL_STUDENT_ID, member.studentId)
        values.put(COL_GENDER, member.gender)
        values.put(COL_PHONE_NUMBER, member.phoneNumber)
        values.put(COL_GRADE, member.grade)
        values.put(COL_MAJOR, member.major)
        values.put(COL_TEAM, member.team)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    // 유저 정보 업데이트 메소드
    fun updateMember(member: Member): Int{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NAME, member.name)
        values.put(COL_STUDENT_ID, member.studentId)
        values.put(COL_GENDER, member.gender)
        values.put(COL_PHONE_NUMBER, member.phoneNumber)
        values.put(COL_GRADE, member.grade)
        values.put(COL_MAJOR, member.major)
        values.put(COL_TEAM, member.team)

        return db.update(TABLE_NAME, values, "$UID=?", arrayOf(member.uid.toString()))
    }

    // 유저 삭제 메소드
    fun deleteMember(member: Member){
        val db = this.writableDatabase

        db.delete(TABLE_NAME,"$UID=?", arrayOf(member.uid.toString()))
        db.close()
    }


    fun drop() {
        val DB_PATH = "/data/data/" + (context?.packageName ?: String)
        val DB_NAME = "$DATABASE_NAME"
        val DB_FULLPATH = "$DB_PATH/databases/$DB_NAME"
        val dbFile = File(DB_FULLPATH)
        if (dbFile.delete()) {
            println(" 삭제 성공")
        } else {
            println(" 삭제 실패")
        }
    }

}