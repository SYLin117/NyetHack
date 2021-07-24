package com.bignerdranch.nyethack

import java.lang.Exception
import java.lang.IllegalStateException

fun main(args: Array<String>) {
    var swordsJuggling: Int? = null
    val isJugglingProficient = (1..3).shuffled().last() == 3
    if (isJugglingProficient) {
        swordsJuggling = 2
    }

    swordsJuggling = swordsJuggling!!.plus(1) //使用!!.很危險,可能造成nullexception
    try {
        proficientcyCheck(swordsJuggling)
    } catch (e: Exception) {
        println(e)
    }
    println("You juggle $swordsJuggling swords!")
}

fun proficientcyCheck(swordJuggling: Int?) {
//    swordJuggling ?: throw IllegalStateException("Player cannot juggle swords") // 自訂錯誤
    checkNotNull(swordJuggling, { "Player can't juggle swords" })// 使用內建函數檢查null,效果同上
}

