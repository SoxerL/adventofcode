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

    // day6
    val waitForIt = WaitForIt()
    val day6Res = waitForIt.execute("day6-input")
    println("result day 6: part 1 sum: ${day6Res.first} part 2 sum: ${day6Res.second}")
    // 6209190 -> 28545089

    // day7
    val camelCards = CamelCards()
    val day7Res = camelCards.execute("day7-input")
    println("result day 7: part 1 sum: ${day7Res.first} part 2 sum: ${day7Res.second}")
    // 253910319 -> 254083736

    // day8
    val wasteland = Wasteland()
    val day8ResPart1 = wasteland.executePart1("day8-input")
    val day8RestPart2 = wasteland.executePart2("day8-input")
    println("result day 8: part 1 sum: $day8ResPart1 part 2 sum: $day8RestPart2")
    // 16343 -> 15299095336639

    // day9
    val mirageMaintenance = MirageMaintenance()
    val day9Res = mirageMaintenance.execute("day9-input")
    println("result day 9: nextElement sum (part1): ${day9Res.second} previousElement sum (part2): ${day9Res.first} ")
    // 1581679977 -> 889

    // day10
    val pipeMaze = PipeMaze()
    val day10Res = pipeMaze.execute("day10-input")
    println("result day 10: sum (part1): ${day10Res.first} sum (part2): ${day10Res.second} ")
    // 7102 -> 363

    // day11
    val cosmicExpansion = CosmicExpansion()
    val day11Res = cosmicExpansion.execute("day11-input")
    println("result day 11: sum (part1): ${day11Res.first} sum (part2): ${day11Res.second} ")
    // 10885634 -> 707505470642
}