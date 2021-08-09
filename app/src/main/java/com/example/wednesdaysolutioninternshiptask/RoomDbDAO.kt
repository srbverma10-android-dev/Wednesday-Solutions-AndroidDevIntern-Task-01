package com.example.wednesdaysolutioninternshiptask

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoomDbDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(result: List<Result>)

    @Query("SELECT * FROM Result WHERE artistName LIKE :ArtistName||'%'")
    fun getArtistByName(ArtistName:String) : List<Result>
}