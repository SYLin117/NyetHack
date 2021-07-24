package com.bignerdranch.nyethack

import java.lang.Exception
import java.lang.IllegalStateException
import kotlin.system.exitProcess

fun main(args: Array<String>) {
//    val player = Player("Ian")
////    player.name = "Ian"
//    println(player.name + "TheBrave")
//    player.castFileball()

////    val name: String = "Madrigal" //宣告類型
//    var healthPoints = 89 // 自動配別類型
//    var isBlessed = true
//    var isImmortal = false

    //aura
//    val auraColor = player.auraColor()
//    val healthStatus = player.formatHealthStatus()
//    if (healthPoints == 100) {
//        println(name + " is in excellent condition!")
//    } else if (healthPoints >= 90) {
//        println(name + " has a few scratch!")
//    } else if (healthPoints >= 75) {
//        println(name + " has some minor wounds!")
//    } else if (healthPoints >= 15) {
//        println(name + " looks pretty hurt.!")
//    } else {
//        println(name + " is in awful condition!")
//    }


//    printPlayerStatus(player)

    //Unit函數(沒有回傳值)
//    castFileball(5)

//    val myDice = Dice()
//    (1..10).forEach { println(myDice.rolledValue) }

//    var currentRoom = Room("Foyer")
//    var currentRoom: Room = TownSquare()
//    println(currentRoom.description())
//    println(currentRoom.load())

    Game.play()

//    val player = Player("Ian")
//    println(player.name)
}


//private fun printPlayerStatus(
//    player: Player
//) {
//    println("(Aura: $player.auraColor)" + "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
//    println("$player.name $player.healthStatus")
//}

private fun castFileball(numFileballs: Int = 2) {
    println("A glass of Fileball springs into existence. (x$numFileballs)")
}

object Game { // singleton 物件，只能存在一個物件
    private val player = Player("Ian")
    private var currentRoom: Room = TownSquare()
    private var worldMap = listOf(
        listOf(currentRoom, Room("Tavern"), Room("Back Room")),
        listOf(Room("Long Corridor"), Room("Generic Room"))
    )

    val abandonedTownSquare = object : TownSquare() {
        override fun load() = ""
    }

    init {
        println("Welcome, adventurer")
    }

    fun play() {
        while (true) {
            //Play NyetHack
            println(currentRoom.description())
            println(currentRoom.load())

            //Player Status
            printPlayerStatus(player)

            print("> Enter your command: ")
//            println("Last command ${readLine()}")
            println(GameInput(readLine()).processCommand())
        }
    }

    private fun printPlayerStatus(
        player: Player
    ) {
        println("(Aura: ${player.auraColor()})" + "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
        println("${player.name}: ${player.healthPoints}")
    }

    // 因為GameInput只跟Game相關，所以使用巢狀類別避免其他程式碼使用他
    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, { "" })//若沒有index:1回傳空字串
        fun processCommand() = when (command.lowercase()) {
            "move" -> move(argument)
            "fight" -> fight()
            "exit", "quit" -> exitProcess(0)
            else -> commandNotFound()
        }

        private fun commandNotFound() = "I'm not quite sure what you're trying to do!"
    }

    private fun move(directionInput: String) =
        try {
            val direction = Direction.valueOf(directionInput.uppercase())
            val newPosition = direction.updateCoordinate(player.currentPosition)
            if (!newPosition.isInBounds) {
                throw IllegalStateException("$direction is out of bounds")
            } else {
            }
            val newRoom = worldMap[newPosition.y][newPosition.x]
            player.currentPosition = newPosition
            currentRoom = newRoom
            "OK, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"

        } catch (e: Exception) {
            "Invalid direction: $directionInput"
        }

    private fun fight() = currentRoom.monster?.let {
        while (player.healthPoints > 0 && it.healthPoints > 0) {
            slay(it)
            Thread.sleep(1000)
        }
        "Combat complete."
    } ?: "There's nothin here to fight."

    private fun slay(monster: Monster) {
        println("${monster.name} did ${monster.attack(player)} damage!")
        println("${player.name} did ${monster.attack(monster)} damage!")

        if (player.healthPoints <= 0) {
            println(">>>> You have been defeated! Thanks for playing. <<<<")
            exitProcess(0)
        }

        if (monster.healthPoints <= 0) {
            println(">>>> ${monster.name} has been defeated! <<<<")
            currentRoom.monster = null
        }
    }
}