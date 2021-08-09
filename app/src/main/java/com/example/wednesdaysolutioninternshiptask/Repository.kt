package com.example.wednesdaysolutioninternshiptask

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private var apiResponse = MutableLiveData<List<Result>>()

    private var roomResponse : MutableLiveData<List<Result>> = MutableLiveData()

    fun getArtistDataFromAPI(artistName : String, context: Context) : MutableLiveData<List<Result>> {

        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "ITUNES_DATABASE"
        ).build()
        val userDao = db.userDao()

        API().postTerm(artistName).enqueue(object  : Callback<Item>{
            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                val res : Item? = response.body()
                if (res != null) {
                    if(!res.results.isNullOrEmpty()){
                        apiResponse.postValue(res.results)
                        val thread = Thread {
                            userDao.insertAll(res.results)
                        }
                        thread.start()
                    }
                }
            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
            }
        })
        return apiResponse
    }

    fun getArtistDataFromRoomDB(artistName: String, context: Context): MutableLiveData<List<Result>> {
        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "ITUNES_DATABASE"
        ).build()
        val userDao = db.userDao()

        val thread = Thread {
            roomResponse.postValue(userDao.getArtistByName(artistName))
        }
        thread.start()
        return roomResponse

    }

}