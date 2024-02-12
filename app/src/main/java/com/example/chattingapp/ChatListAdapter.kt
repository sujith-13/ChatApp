package com.example.chattingapp

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.av.avmessenger.Users

class ChatListAdapter(var list:ArrayList<Users>, var context:Activity): RecyclerView.Adapter<ChatListAdapter.MyViewHolder>() {
    class MyViewHolder(itemview: View) :RecyclerView.ViewHolder(itemview) {
             val name=itemview.findViewById<TextView>(R.id.name1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.chat_list_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
         return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user= list[position]
        holder.name.text=user.userName
        holder.itemView.setOnClickListener{
            val intent= Intent(context,ChatScreen::class.java)
            intent.putExtra("name",user.userName)
            intent.putExtra("id",user.userId)
            context.startActivity(intent)

        }
    }
}