package com.example.chattingapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chattingapp.R
import com.example.chattingapp.models.MsgModelClass
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context,val list:ArrayList<MsgModelClass>) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    companion object {
        private const val ITEM_SEND = 1
        private const val ITEM_RECEIVE = 2
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_SEND) {
            val view = LayoutInflater.from(context).inflate(R.layout.sender_layout, parent, false)
            senderViewHolder(view)
        } else {
            val view = LayoutInflater.from(context).inflate(R.layout.reciver_layout, parent, false)
            reciverViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val messages=list[position]
        if (holder is senderViewHolder) {
            holder.msg.text = messages.message
        } else if (holder is reciverViewHolder) {
            holder.msgs.text = messages.message
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    override fun getItemViewType(position: Int): Int {
        val messages = list[position]
        return if (FirebaseAuth.getInstance().currentUser?.uid == messages.senderId) {
            ITEM_SEND
        } else {
            ITEM_RECEIVE
        }
    }

    class senderViewHolder(itemview:View):RecyclerView.ViewHolder(itemview)
    {
        val msg=itemview.findViewById<TextView>(R.id.sendertxt)

    }
    class reciverViewHolder(itemview:View):RecyclerView.ViewHolder(itemview)
    {
          val msgs=itemview.findViewById<TextView>(R.id.recivertxt)
    }

}
