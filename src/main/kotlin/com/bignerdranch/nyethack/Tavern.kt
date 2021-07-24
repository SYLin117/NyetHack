package com.bignerdranch.nyethack

import java.io.File
import kotlin.math.roundToInt

const val TAVERN_NAME = "Taernyl's Folly"
var playerGold = 10
var playerSilver = 10

//private fun <T> Iterable<T>.random(): T = this.shuffled().first() // 自定義random擴充 // 改定義在IterableExt.kt中,這樣多個檔案都可以用

//val getPatronList: List<String> = listOf("Eli", "Mordoc", "Sophie")
val patronList = mutableListOf<String>("Eli", "Mordoc", "Sophie")//可修改list
val uniquePatron = mutableListOf<String>()
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")

//從檔案中讀取資料
val menuList = File("data/tavern-menu-items.txt")
    .readText()
    .split('\n')

val planets = setOf("Mercury", "Venus", "Earth")

fun main(args: Array<String>) {
//    var signatureDrink = "Buttered Ale"
//    signatureDrink = null //不可以設定已設定類型的參數為null


    // String? 表示回傳可以是String or null
    // 內建函數:fun readLine(): String? = LineReader.readLine(System.`in`, Charset.defaultCharset())
//    var beverage = readLine()?.capitalize() //.?自動判斷是否為null

//    var beverage = readLine()?.let {// 與.?用法相當
//        if (it.isNotBlank()) {
//            it.capitalize()
//        } else {
//            "Buttered Ale"
//        }
//    }


//    var beverage = readLine()
//    if (beverage != null) {
//        beverage = beverage.capitalize()
//    } else {
//        println("I cant do that without crashing")
//    }
//    val beverageServed: String = beverage ?: "Buttered Ale" //若beverage非null則左邊,null則右邊
////    beverage = null
//    println(beverage)


//    placeOrder("Ian", "shandy,Dragon's Breath,5.91")


//    println(getPatronList)
//    getPatronList.remove("Eli")
//    getPatronList.add(0, "Alex")
//    println(getPatronList)
//    getPatronList.forEach { patron ->
//        println("Good morning, $patron")
//    }
//    getPatronList.forEachIndexed { index, patron ->
//        println("Good evening, $patron - you're #${index + 1} in line.")
//        placeOrder(patron, getMenuList.shuffled().first())
//    }

//    getMenuList.forEachIndexed { index, data ->
//        println("$index : $data")
//    }


    (0..9).forEach {
//        val first = patronList.shuffled().first()
        val first = patronList.random()//自訂義的函數
        val last = lastName.random()
        val name = "$first $last"
        uniquePatron += name
    }
    println(uniquePatron)
}

fun performPruchase(price: Double) {
    val totalPurse = playerGold + (playerSilver / 100.0)
    println("Total purse: $totalPurse")
    println("Purchasing item for $price")
    val remainingBalance = totalPurse - price
    println("Remaining balance: ${"%.2f".format(remainingBalance)}")

    val remainingGold = remainingBalance.toInt()
    val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
    playerGold = remainingGold
    playerSilver = remainingSilver
    displayBalance()
}

private fun displayBalance() {
    println("Player's purse balance: Gold $playerGold, Silver: $playerSilver")
}

private fun placeOrder(player: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Madrigal speaks with $tavernMaster about their order.")

    val data = menuData.split(',')
//    val type = data[0]
//    val name = data[1]
//    val price = data[2]
    val (type, name, price) = menuData.split(',') // 效果同上
    val message = "$player buys a $name ($type) for $price."
    println(message)

    performPruchase(price.toDouble())

    val phrase = "Ah, delicious $name!"
    println("Madrigal exclaims: ${toDragonSpeak(phrase)}")
}

private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aeiou]")) {
        when (it.value) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }