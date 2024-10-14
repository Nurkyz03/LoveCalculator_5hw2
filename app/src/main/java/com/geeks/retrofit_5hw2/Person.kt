package com.geeks.retrofit_5hw2

class Person (
    val name: String,
    val lastName:String,
    val dateOfBirth: String,
    val height: Int,
    val weight: Int,
    val address: String,
    val sex: String,
    val job: Job
)

class Job (
    val name: String,
    val address: String,
    val schedule: String,
    val salary: Int,
    val nameOfColleague: List<String>
)