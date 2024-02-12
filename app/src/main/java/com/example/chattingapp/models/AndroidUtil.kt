package com.example.chattingapp.models

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import android.widget.Toast
import com.av.avmessenger.Users


class AndroidUtil {
    companion object{
    fun showToast(context: Context?, message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun passUserModelAsIntent(intent: Intent, model: Users) {
        intent.putExtra("username", model.userName)

        intent.putExtra("userId", model.userId)
        intent.putExtra("fcmToken", model.fcmtoken)
    }}

//    fun getUserModelFromIntent(intent: Intent): Users? {
//        val userModel = Users()
//        userModel.setUsername(intent.getStringExtra("username"))
//        userModel.setPhone(intent.getStringExtra("phone"))
//        userModel.setUserId(intent.getStringExtra("userId"))
//        userModel.setFcmToken(intent.getStringExtra("fcmToken"))
//        return userModel
//    }


}