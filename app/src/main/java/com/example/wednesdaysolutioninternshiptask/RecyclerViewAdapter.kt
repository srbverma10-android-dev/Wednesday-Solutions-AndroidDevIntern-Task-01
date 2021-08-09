package com.example.wednesdaysolutioninternshiptask

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wednesdaysolutioninternshiptask.databinding.RecyclerViewBinding

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private lateinit var items : List<Result>

    fun setDataList(data: List<Result>){
        this.items = data
    }

    class MyViewHolder(private val binding: RecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : Result){
            binding.resultItem = data
            binding.executePendingBindings()
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        items[position].let {
            holder.bind(it)
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }
    companion object{
        @BindingAdapter("loadImage")
        @JvmStatic
        fun loadImage(imageView: ImageView, url: String){
            Glide.with(imageView).load(url).into(imageView)
        }
    }
}