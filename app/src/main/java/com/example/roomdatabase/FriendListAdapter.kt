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
        val updateFriend = view.findViewById<Button>(R.id.updateFriend)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.friendRating.text = friendList[position].rating.toString()
        holder.friendName.text = friendList[position].firstName

        holder.updateFriend.setOnClickListener {
            val updatedFriend = Friend(
                uid = friendList[position].uid,
                firstName = holder.friendName.text.toString(),
                rating = holder.friendRating.text.toString().toInt()
            )
            eventListener?.onUpdateFriend(updatedFriend)
        }
        holder.removeFriend.setOnClickListener { eventListener?.onDeleteFriend(friendList[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.adapter_friend_list, parent, false))
    }

    override fun getItemCount() = friendList.size

    private var eventListener: EventListener? = null
    fun setEventListener(eventListener: EventListener) {
        this.eventListener = eventListener;
    }

    interface EventListener {
        fun onDeleteFriend(DeletedFriend: Friend)
        fun onUpdateFriend(updatedFriend: Friend)
    }

    fun updateData( updatedfriendList: List<Friend> ) {
        friendList.clear()
        friendList.addAll(updatedfriendList)
        notifyDataSetChanged()
    }
}