import `2023`.CubeConundrum
import `2023`.Trebuchet

fun main() {

    // 2023
    // day 1
    val trebuchet = Trebuchet()
    println("result day 1: ${trebuchet.execute("day1-input")}")
    // day 2
    val cubeConundrum = CubeConundrum()
    val day2Res = cubeConundrum.executePart1("day2-input", 12, 13, 14)
    println("result day 2: part 1 sum: ${day2Res.first} part 2 power: ${day2Res.second}")
}