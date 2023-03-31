package com.example.icarus.dto

data class Sincerity
    (val uid: Int,
     val studentId: String,
     val name: String,
     var oneCount: Int,
     var twoCount: Int,
     var threeCount: Int,
     var sumCount: Int
    ){

        constructor() : this (0,"","",0,0,0,0)

    }