package com.av.avmessenger

class Users {
    var mail: String = ""
        get() = field
        set(value) {
            field = value
        }

    var userName: String = ""
        get() = field
        set(value) {
            field = value
        }

    var password: String = ""
        get() = field
        set(value) {
            field = value
        }

    var userId: String = ""
        get() = field
        set(value) {
            field = value
        }
    var fcmtoken:String=""
        get()=field
        set(value){
            field=value
        }
//    var lastMessage: String = ""
//        get() = field
//        set(value) {
//            field = value
//        }

    constructor() {}

    constructor(
        userId: String,
        userName: String,
        mail: String,
        password: String,
        //fcmtoken:String
       // lastMessage: String
    ) {
        this.userId = userId
        this.userName = userName
        this.mail = mail
        this.password = password
       // this.fcmtoken=fcmtoken
       // this.lastMessage = lastMessage
    }
}
