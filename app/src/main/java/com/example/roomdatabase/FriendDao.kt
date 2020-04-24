package com.example.roomdatabase

import androidx.room.*

@Dao
interface FriendDao {

    @Query("select * from friend")
    fun getAllFriends() : MutableList<Friend>

    @Insert
    fun insertFriend(friend: Friend)

    @Delete()
    fun deleteFriend(friend: Friend)

    @Update
    fun updateFriend(friend: Friend)
}