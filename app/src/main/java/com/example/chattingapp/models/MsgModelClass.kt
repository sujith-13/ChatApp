package com.example.chattingapp.models

class MsgModelClass {
    var message: String?=null
    var senderId: String?=null
    var timeStamp: Long = 0

    constructor() {}

    constructor(message: String?, senderId: String?, timeStamp: Long) {
        this.message = message
        this.senderId = senderId
        this.timeStamp = timeStamp
    }
}
