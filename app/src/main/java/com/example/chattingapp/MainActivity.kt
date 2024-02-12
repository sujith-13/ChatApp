package com.example.chattingapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.av.avmessenger.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var reference:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView=findViewById<RecyclerView>(R.id.chatview)

        recyclerView.layoutManager=LinearLayoutManager(this)
        val list= ArrayList<Users>();
        val database=FirebaseDatabase.getInstance()
        auth=FirebaseAuth.getInstance()
        val adapter=ChatListAdapter(list,this)
         reference=database.getReference().child("user")
        reference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(snap in snapshot.children)
                {
                    val user=snap.getValue(Users::class.java)
                    if (user != null && user.userId!=auth.uid) {
                        list.add(user)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        recyclerView.adapter=adapter

        val logout=findViewById<ImageButton>(R.id.logout)
        logout.setOnClickListener{
            val builder= AlertDialog.Builder(this)
            builder.setTitle("LogOut")
            builder.setMessage("Do You Really Want To Logout")
            builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(this,"You have selected Yes", Toast.LENGTH_SHORT).show()
                auth.signOut()
                val intent= Intent(this,Sign_in::class.java)
                startActivity(intent)
                finish()

            })
            builder.setNegativeButton("NO", DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(this,"You have Selected No", Toast.LENGTH_SHORT).show()
                //answers.set(0, arrayOf("No"))

            })
            builder.show()
        }
getFCMToken()
    }

    fun getFCMToken(){
        var token=""
        FirebaseMessaging.getInstance().token.addOnCompleteListener{
            if(it.isSuccessful)
            {
                token=it.result
                Toast.makeText(this,token,Toast.LENGTH_SHORT).show()
                FirebaseUtil.currentUserDetails()?.update("fcmtoken",token)
            }
        }

    }
}
