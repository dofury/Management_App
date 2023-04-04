package com.example.icarus.dto

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.File
import kotlin.math.sin

class DBHelper(
    private val context: Context?,
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "ICARUS"

        // Table
        const val TABLE_NAME1 = "MEMBERS"
        const val UID = "UID"
        const val COL_STUDENT_ID = "STUDENT_ID"
        const val COL_NAME = "NAME"
        const val COL_GENDER = "GENDER"
        const val COL_PHONE_NUMBER = "PHONE_NUMBER"
        const val COL_GRADE = "GRADE"
        const val COL_AGE = "AGE"
        const val COL_CAMPUS = "CAMPUS"
        const val COL_MAJOR = "MAJOR"
        const val COL_FIELD = "FIELD"
        const val COL_LEVEL = "LEVEL"
        const val COL_TEAM = "TEAM"

        const val TABLE_NAME2 = "SINCERITY"
        const val COL_ONE_COUNT = "ONE_COUNT"
        const val COL_TWO_COUNT = "TWO_COUNT"
        const val COL_THREE_COUNT = "THREE_COUNT"
        const val COL_SUM_COUNT = "SUM_COUNT"

    }
    override fun onCreate(db: SQLiteDatabase) {
        val sql1: String = "CREATE TABLE if not exists " +
                "$TABLE_NAME1 ($UID integer primary key autoincrement, " +
                "$COL_STUDENT_ID text, $COL_NAME text, $COL_GENDER text, $COL_AGE integer,$COL_PHONE_NUMBER text, $COL_GRADE text,$COL_CAMPUS text, $COL_MAJOR text,$COL_FIELD text,$COL_LEVEL text, $COL_TEAM text);"

        val sql2: String = "CREATE TABLE if not exists " +
                "$TABLE_NAME2 ($UID integer primary key autoincrement, " +
                "$COL_STUDENT_ID text, $COL_NAME text, $COL_ONE_COUNT integer, $COL_TWO_COUNT integer,$COL_THREE_COUNT integer, $COL_SUM_COUNT integer);"

        db.execSQL(sql1)
        db.execSQL(sql2)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql1: String = "DROP TABLE if exists $TABLE_NAME1"
        val sql2: String = "DROP TABLE if exists $TABLE_NAME2"
        db.execSQL(sql2)
        onCreate(db)
    }

    val allMembers:MutableList<Member>
        @SuppressLint("Range")
        get() {
            val members = ArrayList<Member>()
            val selectQueryHandler = "SELECT * FROM $TABLE_NAME1"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQueryHandler,null)
            if(cursor.moveToFirst()){
                do{
                    val member = Member(
                        cursor.getInt(cursor.getColumnIndex(UID)),
                        cursor.getString(cursor.getColumnIndex(COL_STUDENT_ID)),
                        cursor.getString(cursor.getColumnIndex(COL_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_GENDER)),
                        cursor.getInt(cursor.getColumnIndex(COL_AGE)),
                        cursor.getString(cursor.getColumnIndex(COL_PHONE_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(COL_GRADE)),
                        cursor.getString(cursor.getColumnIndex(COL_CAMPUS)),
                        cursor.getString(cursor.getColumnIndex(COL_MAJOR)),
                        cursor.getString(cursor.getColumnIndex(COL_FIELD)),
                        cursor.getString(cursor.getColumnIndex(COL_LEVEL)),
                        cursor.getString(cursor.getColumnIndex(COL_TEAM))
                    )
                    members.add(member)
                }while(cursor.moveToNext())
            }
            db.close()
            return members
        }

    val allSincerity:MutableList<Sincerity>
        @SuppressLint("Range")
        get() {
            val sinceritys = ArrayList<Sincerity>()
            val selectQueryHandler = "SELECT * FROM $TABLE_NAME2"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQueryHandler,null)
            if(cursor.moveToFirst()){
                do{
                    val sincerity = Sincerity(
                        cursor.getInt(cursor.getColumnIndex(UID)),
                        cursor.getString(cursor.getColumnIndex(COL_STUDENT_ID)),
                        cursor.getString(cursor.getColumnIndex(COL_NAME)),
                        cursor.getInt(cursor.getColumnIndex(COL_ONE_COUNT)),
                        cursor.getInt(cursor.getColumnIndex(COL_TWO_COUNT)),
                        cursor.getInt(cursor.getColumnIndex(COL_THREE_COUNT)),
                        cursor.getInt(cursor.getColumnIndex(COL_SUM_COUNT))
                    )
                     sinceritys.add(sincerity)
                }while(cursor.moveToNext())
            }
            db.close()
            return sinceritys
        }
    @SuppressLint("Range")
    fun getSincerity(studentId: String): Sincerity{
        var sincerity = Sincerity()
        val selectQueryHandler = "SELECT * FROM $TABLE_NAME2 WHERE $COL_STUDENT_ID= ?"

        val db = readableDatabase
        val cursor = db.rawQuery(selectQueryHandler, arrayOf(studentId))
        if(cursor.moveToFirst()){
            do{
                sincerity = Sincerity(
                    cursor.getInt(cursor.getColumnIndex(UID)),
                    cursor.getString(cursor.getColumnIndex(COL_STUDENT_ID)),
                    cursor.getString(cursor.getColumnIndex(COL_NAME)),
                    cursor.getInt(cursor.getColumnIndex(COL_ONE_COUNT)),
                    cursor.getInt(cursor.getColumnIndex(COL_TWO_COUNT)),
                    cursor.getInt(cursor.getColumnIndex(COL_THREE_COUNT)),
                    cursor.getInt(cursor.getColumnIndex(COL_SUM_COUNT))
                )
            }while(cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return sincerity

    }

    @SuppressLint("Range")
    fun getSincerityName(name: String): Sincerity{
        var sincerity = Sincerity()
        val selectQueryHandler = "SELECT * FROM $TABLE_NAME2 WHERE $COL_NAME= ?"

        val db = readableDatabase
        val cursor = db.rawQuery(selectQueryHandler, arrayOf(name))
        if(cursor.moveToFirst()){
            do{
                sincerity = Sincerity(
                    cursor.getInt(cursor.getColumnIndex(UID)),
                    cursor.getString(cursor.getColumnIndex(COL_STUDENT_ID)),
                    cursor.getString(cursor.getColumnIndex(COL_NAME)),
                    cursor.getInt(cursor.getColumnIndex(COL_ONE_COUNT)),
                    cursor.getInt(cursor.getColumnIndex(COL_TWO_COUNT)),
                    cursor.getInt(cursor.getColumnIndex(COL_THREE_COUNT)),
                    cursor.getInt(cursor.getColumnIndex(COL_SUM_COUNT))
                )
            }while(cursor.moveToNext())
        }


        cursor.close()
        db.close()
        return sincerity

    }

    @SuppressLint("Range")
    fun getMemberName(name: String): MutableList<Member>{
        var members = mutableListOf<Member>()
        val selectQueryHandler = "SELECT * FROM $TABLE_NAME1 WHERE $COL_NAME= ?"

        val db = readableDatabase
        val cursor = db.rawQuery(selectQueryHandler, arrayOf(name))
        if(cursor.moveToFirst()){
            do{
                val member = Member(
                    cursor.getInt(cursor.getColumnIndex(UID)),
                    cursor.getString(cursor.getColumnIndex(COL_STUDENT_ID)),
                    cursor.getString(cursor.getColumnIndex(COL_NAME)),
                    cursor.getString(cursor.getColumnIndex(COL_GENDER)),
                    cursor.getInt(cursor.getColumnIndex(COL_AGE)),
                    cursor.getString(cursor.getColumnIndex(COL_PHONE_NUMBER)),
                    cursor.getString(cursor.getColumnIndex(COL_GRADE)),
                    cursor.getString(cursor.getColumnIndex(COL_CAMPUS)),
                    cursor.getString(cursor.getColumnIndex(COL_MAJOR)),
                    cursor.getString(cursor.getColumnIndex(COL_FIELD)),
                    cursor.getString(cursor.getColumnIndex(COL_LEVEL)),
                    cursor.getString(cursor.getColumnIndex(COL_TEAM))
                )
                members.add(member)
            }while(cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return members

    }
    fun allAddMember(members: MutableList<Member>){
        drop()
        for(member in members){
            addMember(member)
        }
    }
    fun addMember(member: Member){
        val db = this.writableDatabase
        val values = ContentValues()

        val sincerity = Sincerity(member.uid,member.studentId,member.name,0,0,0,0)

        values.put(COL_NAME, member.name)
        values.put(COL_STUDENT_ID, member.studentId)
        values.put(COL_GENDER, member.gender)
        values.put(COL_AGE, member.age)
        values.put(COL_PHONE_NUMBER, member.phoneNumber)
        values.put(COL_CAMPUS, member.campus)
        values.put(COL_MAJOR, member.major)
        values.put(COL_GRADE, member.grade)
        values.put(COL_FIELD, member.field)
        values.put(COL_LEVEL, member.level)
        values.put(COL_TEAM, member.team)



        db.insert(TABLE_NAME1, null, values)
        addSincerity(sincerity)
        db.close()
    }

    fun addSincerity(sincerity: Sincerity){
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(COL_NAME, sincerity.name)
        values.put(COL_STUDENT_ID, sincerity.studentId)
        values.put(COL_ONE_COUNT, sincerity.oneCount)
        values.put(COL_TWO_COUNT, sincerity.twoCount)
        values.put(COL_THREE_COUNT, sincerity.threeCount)
        values.put(COL_SUM_COUNT, sincerity.sumCount)

        db.insert(TABLE_NAME2, null, values)
        db.close()
    }

    // 유저 정보 업데이트 메소드
    fun updateMember(member: Member): Int{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NAME, member.name)
        values.put(COL_STUDENT_ID, member.studentId)
        values.put(COL_GENDER, member.gender)
        values.put(COL_AGE, member.age)
        values.put(COL_PHONE_NUMBER, member.phoneNumber)
        values.put(COL_GRADE, member.grade)
        values.put(COL_CAMPUS, member.campus)
        values.put(COL_MAJOR, member.major)
        values.put(COL_FIELD, member.field)
        values.put(COL_LEVEL, member.level)
        values.put(COL_TEAM, member.team)

        return db.update(TABLE_NAME1, values, "$COL_STUDENT_ID=?", arrayOf(member.studentId.toString()))
    }

    fun updateSincerity(sincerity: Sincerity): Int{
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(COL_NAME, sincerity.name)
        values.put(COL_STUDENT_ID, sincerity.studentId)
        values.put(COL_ONE_COUNT, sincerity.oneCount)
        values.put(COL_TWO_COUNT, sincerity.twoCount)
        values.put(COL_THREE_COUNT, sincerity.threeCount)
        values.put(COL_SUM_COUNT, sincerity.sumCount)

        return db.update(TABLE_NAME2, values, "$COL_STUDENT_ID=?", arrayOf(sincerity.studentId))
    }

    // 유저 삭제 메소드
    fun deleteMember(member: Member){
        val db = this.writableDatabase

        db.delete(TABLE_NAME1,"$COL_STUDENT_ID=?", arrayOf(member.studentId))
        db.delete(TABLE_NAME2,"$COL_STUDENT_ID=?", arrayOf(member.studentId))//성실도도 같이 삭제
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