package com.example.icarus.dto

data class Member(
    var uid: Int,
    var studentId: String,
    var name: String,
    var gender: String,
    var age: Int,
    var phoneNumber: String,
    var grade: String,
    var campus: String,
    var major: String,
    var field: String,
    var level: String,
    var team: String

){
    constructor() : this (0,"","","",0,"","","","","","","")
}
