package com.example.roomdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FriendListAdapter( private var friendList: MutableList<Friend> ) : RecyclerView.Adapter<FriendListAdapter.ViewHolder>() {


    class ViewHolder( val view: View) : RecyclerView.ViewHolder(view){
        val friendName = view.findViewById<TextView>(R.id.friendName)
        val friendRating = view.findViewById<TextView>(R.id.friendRating)
        val removeFriend = view.findViewById<Button>(R.id.removeFriend)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.removeFriend.setOnClickListener {
            onItemClickListener?.onItemClick(holder.itemView, position)
        }
        holder.friendRating.text = friendList[position].rating.toString()
        holder.friendName.text = friendList[position].firstName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.adapter_friend_list, parent, false))
    }

    override fun getItemCount() = friendList.size

    private var onItemClickListener: ItemClickListener? = null
    fun setItemClickListener(clickItemListener: ItemClickListener) {
        onItemClickListener = clickItemListener;
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    fun updateData( updatedfriendList: List<Friend> ) {
        friendList.clear()
        friendList.addAll(updatedfriendList)
        notifyDataSetChanged()
    }
}