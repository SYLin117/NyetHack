package com.bignerdranch.nyethack

import com.bignerdranch.nyethack.extensions.random as randomizer
import com.sun.xml.internal.bind.v2.runtime.Coordinator
import java.io.File
import java.util.*

@ExperimentalStdlibApi
private fun String.capitalized(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase())
            it.titlecase(Locale.getDefault())
        else it.toString()
    }
}

@ExperimentalStdlibApi
class Player(
    _name: String,
    override var healthPoints: Int = 100,//直接宣告並建構
    val isBlessed: Boolean,
    private val isImmortal: Boolean,
) : Fightable {
    //    var weapon: Weapon? = Weapon("Ebony kris")
    @ExperimentalStdlibApi
    var name = _name
        get() = "${field.capitalized()} of $hometown"
        set(value) {
            field = value.trim()
        }

    //    val hometown = selectHometown()
    val hometown by lazy { selectHometown() } // 惰性初始化
    var currentPosition = Coordinate(0, 0)

    override val diceCount: Int = 3
    override val diceSides: Int = 6

    //因為介面已經實作get()了
//    override val damageRoll: Int
//        get() = super.damageRoll


    override fun attack(opponent: Fightable): Int {
        val damageDealt = if (isBlessed) {
            damageRoll * 2
        } else {
            damageRoll
        }
        opponent.healthPoints -= damageDealt
        return damageDealt
    }

    init { // 初始化區塊 檢查建構函數的有效性
        require(healthPoints > 0, { "healthPoints must be greater than zero." })
        require(name.isNotBlank(), { "Player must have a name." })
    }

    constructor(name: String) : this( //次建構函數
        name,
//        healthPoints = 100, // 在主建構函數已設預設值
        isBlessed = true,
        isImmortal = false
    ) {
        if (name.lowercase() == "kar") healthPoints = 40
    }

    fun castFileball(numFireballs: Int = 2) = println("A glass of Fireball springs into existence. (x$numFireballs)")

    //單運算式函數
    fun formatHealthStatus() =
        when (healthPoints) {
            100 -> "is in excellent condition"
            in 90..99 -> "has a few scratches."
            in 75..89 -> if (isBlessed) {
                "has some minor wounds but is healing quite quickly!"
            } else {
                "has some minor wounds."
            }
            in 15..74 -> "looks pretty hurt."
            else -> "is in awful condition!"
        }

    fun auraColor(): String {
        var auraVisible = isBlessed && healthPoints > 50 || isImmortal
        val auraColor = if (auraVisible) "GREEN" else "NONE"
        return auraColor
    }

    private fun selectHometown() = File("data/towns.txt")
        .readText()
        .split("\n")
        .randomizer()

//    fun printWeaponName() {
////        if (weapon != null) {
////            println(weapon.name)
////        }
//        weapon?.also { println(it.name) }//also表示安全後呼叫
//    }
}

