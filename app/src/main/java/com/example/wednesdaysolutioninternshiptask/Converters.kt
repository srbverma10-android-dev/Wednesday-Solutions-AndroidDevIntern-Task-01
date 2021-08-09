package com.example.wednesdaysolutioninternshiptask

import androidx.room.TypeConverter
import org.jetbrains.annotations.Nullable

class Converters {

    @TypeConverter
    fun toList(strings: String): List<String> {
        val list = mutableListOf<String>()
        if (strings.isNotEmpty()) {
            val array = strings.split(",")
            for (s in array) {
                list.add(s)
            }
        } else {
            list.add("null")
        }
        return list
    }

    @TypeConverter
    fun toString(@Nullable strings: List<String>?): String {
        var result = ""
        if (!strings.isNullOrEmpty()) {
            if (!strings.isNullOrEmpty()) {
                strings.forEachIndexed { index, element ->
                    result += element
                    if(index != (strings.size-1)){
                        result += ","
                    }
                }
            }
        }
        return result
    }
}