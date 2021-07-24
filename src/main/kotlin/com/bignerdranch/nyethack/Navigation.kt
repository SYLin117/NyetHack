package com.bignerdranch.nyethack

// 資料類別，用來儲存資料用
data class Coordinate(val x: Int, val y: Int) {
    val isInBounds = x >= 0 && y >= 0

    // +
    operator fun plus(other: Coordinate) = Coordinate(x + other.x, y + other.y)
}

// 列舉類別用來定義常數集合
enum class Direction(private val coordinate: Coordinate) {
    NORTH(Coordinate(0, -1)),
    EAST(Coordinate(1, 0)),
    SOUTH(Coordinate(0, 1)),
    WEST(Coordinate(-1, 0));

    fun updateCoordinate(playerCoordinate: Coordinate) =
//        Coordinate(playerCoordinate.x + coordinate.x, playerCoordinate.y + coordinate.y)
        coordinate + playerCoordinate //使用重載的運算子

}

fun main(args: Array<String>) {
    Direction.EAST.updateCoordinate(Coordinate(1, 0))
}