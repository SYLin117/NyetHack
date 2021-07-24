package com.bignerdranch.nyethack

fun main(args: Array<String>) {
    val townSquare = TownSquare()
    println(townSquare.dangerLevel)
}

// open表示可被繼承
open class Room(var name: String) {
    protected open val dangerLever = 5
    var monster: Monster? = Goblin()
    fun description() =
        "Room: $name\n" +
                "Danger level: $dangerLever\n" +
                "Creature: ${monster?.description ?: "none."}"

    open fun load() = "Nothing much to see here..."


}

open class TownSquare : Room("Town Square") {
    val dangerLevel = super.dangerLever - 3
    private var bellSound = "GWONG"
    open override fun load() = "The villagers rally and cheer as you enter!\n ${ringBell()}"
    private fun ringBell() = "The bell tower announces your arrival. $bellSound"
}

fun printIsSourceOfBlessings(any: Any) {
    val isSourceOfBlessings = if (any is Player) {
        any.isBlessed
    } else {
        (any as Room).name = "Fount of Blessings"
    }
    println("$any is a source of blessings: $isSourceOfBlessings")
}