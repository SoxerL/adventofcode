import `2023`.*

fun main() {

    // 2023
    // day 1
    val trebuchet = Trebuchet()
    println("result day 1: ${trebuchet.execute("day1-input")}")
    // 53974 -> 52840

    // day 2
    val cubeConundrum = CubeConundrum()
    val day2Res = cubeConundrum.executePart1("day2-input", 12, 13, 14)
    println("result day 2: part 1 sum: ${day2Res.first} part 2 power: ${day2Res.second}")
    // 2156 -> 66909

    //day3
    val gearRatios = GearRatios()
    val day3Res = gearRatios.execute("day3-input")
    println("result day 3: part 1 sum: ${day3Res.first} part 2 sum: ${day3Res.second}")
    // 553079 -> 84363105

    // day4
    val scratchcards = Scratchcards()
    val day4Res = scratchcards.execute("day4-input")
    println("result day 4: part 1 sum: ${day4Res.first} part 2 sum: ${day4Res.second}")
    // 23673 -> 12263631

    // day5
    val fertilizer = Fertilizer()
    val day5Res = fertilizer.execute("day5-input")
    println("result day 5: part 1 sum: ${day5Res.first} part 2 sum: ${day5Res.second}")
    // 289863851 -> 60568880
}