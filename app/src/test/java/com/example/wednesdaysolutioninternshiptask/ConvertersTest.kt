package com.example.wednesdaysolutioninternshiptask

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ConvertersTest {

    @Test
    fun toList_returnTrue(){
        val result = Converters().toList("hello,sourabh,kumar,verma")
        assertThat(result).isEqualTo(listOf("hello", "sourabh", "kumar", "verma"))
    }

    @Test
    fun toList_returnFalse(){
        val result = Converters().toList("hello,sourabh,kumar,verma")
        assertThat(result).isNotEqualTo(listOf("hello", "sourabh", "kumar", "verma", "hahaha!!this will be false...lol"))
    }

    @Test
    fun testToString_returnTrue() {

        val result = Converters().toString(listOf("hello", "sourabh", "kumar", "verma"))
        assertThat(result).isEqualTo("hello,sourabh,kumar,verma")

    }

    @Test
    fun testToString_returnFalse() {

        val result = Converters().toString(listOf("hello", "sourabh", "kumar", "verma", "hahaha!!! it will return false..lol"))
        assertThat(result).isNotEqualTo("hello,sourabh,kumar,verma")

    }

}