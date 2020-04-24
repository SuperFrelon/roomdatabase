package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Room.databaseBuilder(
            this,
            FriendDatabase::class.java, "friend_database"
        )
            .allowMainThreadQueries()
            .build()

        val newFriend = Friend(
            firstName = "Bob",
            rating = 99
        )
        database.friendDao().insertFriend(newFriend)

        var allFriends = database.friendDao().getAllFriends()

        val viewManager = LinearLayoutManager(this)
        val viewAdapter = FriendListAdapter(allFriends)
        viewAdapter.setItemClickListener(
            object: FriendListAdapter.ItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    val toRemove = allFriends[position]
                    database.friendDao().deleteFriend(toRemove)
                    allFriends = database.friendDao().getAllFriends()
                    viewAdapter.updateData( allFriends );
                }
            }
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recylerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
