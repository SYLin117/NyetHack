package com.bignerdranch.nyethack.extensions

fun <T> Iterable<T>.random(): T = this.shuffled().first() // 自定義random擴充