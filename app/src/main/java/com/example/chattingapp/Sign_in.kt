package com.example.chattingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Sign_in : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val tv=findViewById<TextView>(R.id.register)
        tv.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,Register::class.java))
            finish()
        })
        val userid=findViewById<EditText>(R.id.uidtedtext)
        val pass=findViewById<EditText>(R.id.passedtext)
        val auth=FirebaseAuth.getInstance()
        val btn=findViewById<Button>(R.id.login)
        btn.setOnClickListener{
            val id=userid.text.toString().trim()
            val pas=pass.text.toString().trim()
            Toast.makeText(this,id,Toast.LENGTH_SHORT).show()
            Toast.makeText(this,pas,Toast.LENGTH_SHORT).show()
            auth.signInWithEmailAndPassword(id,pas).addOnCompleteListener{
                if(it.isSuccessful)
                {
                    Toast.makeText(this,"Logged In",Toast.LENGTH_SHORT).show()
                   val intent=Intent(this@Sign_in,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this,"Try Again",Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
}