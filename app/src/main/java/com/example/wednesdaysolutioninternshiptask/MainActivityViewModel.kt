package com.example.wednesdaysolutioninternshiptask

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private var repository: Repository = Repository()
    private var recyclerListData : MutableLiveData<List<Result>> = MutableLiveData()
    private var recyclerViewAdapter : RecyclerViewAdapter = RecyclerViewAdapter()

    @RequiresApi(Build.VERSION_CODES.M)
    fun onTextChanged(s: CharSequence, context: Context){
        if (isOnline(context)) {
            recyclerListData.postValue(repository.getArtistDataFromAPI(s.toString(), context).value)
        } else {
            recyclerListData.postValue(repository.getArtistDataFromRoomDB(s.toString(), context).value)
        }
    }

    fun recyclerListDataObserver() : MutableLiveData<List<Result>>{
        return recyclerListData
    }

    fun getAdapter() : RecyclerViewAdapter{
        return recyclerViewAdapter
    }

    fun setAdapterData(data : List<Result>){
        recyclerViewAdapter.setDataList(data)
        recyclerViewAdapter.notifyItemRangeChanged(0, data.size-1)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }
        return false
    }

}