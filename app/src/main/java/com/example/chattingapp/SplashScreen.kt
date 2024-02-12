package com.example.chattingapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.av.avmessenger.Users
import com.example.chattingapp.models.AndroidUtil
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        if (intent.extras != null) {
            //from notification
            val userId = intent.extras!!.getString("userId")
            FirebaseUtil.allUserCollectionReference().document(userId!!).get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val model: Users? = task.result.toObject(Users::class.java)
                        val mainIntent = Intent(this, MainActivity::class.java)
                        mainIntent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                        startActivity(mainIntent)
                        val intent = Intent(this, ChatScreen::class.java)
                        if (model != null) {
                            AndroidUtil.passUserModelAsIntent(intent, model)
                        }
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()
                    }
                }
        }
          val ref=FirebaseAuth.getInstance()
        Handler(Looper.getMainLooper()).postDelayed({
            if(FirebaseAuth.getInstance().uid==null){
            startActivity(Intent(this,Sign_in::class.java))
            finish()}
            else{
                startActivity(Intent(this,MainActivity::class.java))
                finish()
        }
        },3000)



    }
}