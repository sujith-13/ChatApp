package com.example.chattingapp

import android.content.Intent
import android.media.MediaPlayer.OnCompletionListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.chattingapp.models.MsgModelClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Date

class ChatScreen : AppCompatActivity() {
    lateinit var adapter: MessageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_screen)
        val receivername=intent.getStringExtra("name")
        val topname=findViewById<TextView>(R.id.receivername)
        val message=findViewById<EditText>(R.id.message)
        val receiverid=intent.getStringExtra("id")
        val auth=FirebaseAuth.getInstance()
        val senderUID=auth.uid
        val list=ArrayList<MsgModelClass>()
        val senderRoom=senderUID+receiverid
        val receiverRoom=receiverid+senderUID
        val database=FirebaseDatabase.getInstance()
        topname.text=receivername
        val sendbtn=findViewById<ImageButton>(R.id.sendmessage)
        val linearLayoutManager=LinearLayoutManager(this)
        linearLayoutManager.stackFromEnd=true
        val recyclerview=findViewById<RecyclerView>(R.id.recycle)
        recyclerview.layoutManager=linearLayoutManager
        adapter=MessageAdapter(this,list)

        recyclerview.adapter=adapter
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver(){
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
              recyclerview.smoothScrollToPosition(0)
        }})
        val chatRefernce=database.getReference().child("chats").child(senderRoom).child("messages")
        chatRefernce.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for(snap in snapshot.children)
                {
                    val m=snap.getValue(MsgModelClass::class.java)

                    if (m != null) {

                        list.add(m)
                    }

                    adapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        sendbtn.setOnClickListener{
            val mes=message.text.toString()
            if(mes.isEmpty())
            {
                Toast.makeText(this,"Enter the message First",Toast.LENGTH_SHORT).show()
            }
            message.text.clear()
            val date=Date()
            val messages= MsgModelClass(mes,senderUID,date.time)
            database.getReference().child("chats").child(senderRoom).child("messages").push().setValue(messages)
                .addOnCompleteListener {
                    if(it.isComplete)
                    {
                        database.getReference().child("chats").child(receiverRoom).child("messages").push().setValue(messages)
                            .addOnCompleteListener {

                            }
                    }
            }
            val back=findViewById<ImageButton>(R.id.back)
            back.setOnClickListener {
                val intent= Intent(this@ChatScreen,MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
       
    }
}