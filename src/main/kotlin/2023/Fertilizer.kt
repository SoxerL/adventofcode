package `2023`

/**
 * 2023 - Day5
 * Given seeds calculate their placed locations according to the mappings
 * part 1: lowest location according to seed inputs
 * Part 2: same as part 1 but input seeds are not ranges as well
 */
class Fertilizer {

    /**
     * Returns a Pair of sets of which the first is leftover original which still can be mapped
     * and the second is already target mapped and should not be further evaluated until next mapping round occurs
     */
    fun splitRangesAndMap(originalRange: LongRange, sourceRange: LongRange, targetRange: LongRange): Pair<Set<LongRange>, Set<LongRange>> {
        val rangesToConsider = mutableSetOf<LongRange>()
        val rangesMapped = mutableSetOf<LongRange>()
        if (originalRange.first < sourceRange.first) {
            rangesToConsider.add(originalRange.first until sourceRange.first)
            if (originalRange.last > sourceRange.last) {
                // source inside Original
                rangesMapped.add(targetRange)
                rangesToConsider.add((sourceRange.last + 1)..originalRange.last)
            } else {
                // source overlapping original
                val overlap = originalRange.last - sourceRange.first
                rangesMapped.add(targetRange.first..(targetRange.first + overlap))
            }
        } else {
            if (sourceRange.last >= originalRange.last) {
                // original inside source
                val offset = originalRange.first - sourceRange.first
                val originalSize = originalRange.last - originalRange.first
                rangesMapped.add((targetRange.first + offset)..(targetRange.first + offset + originalSize))
            } else {
                // original overlapping source
                val overlap = sourceRange.last - originalRange.first
                rangesMapped.add((targetRange.last - overlap)..targetRange.last)
                rangesToConsider.add((originalRange.first + overlap + 1).. originalRange.last)
            }
        }
        return Pair(rangesToConsider, rangesMapped)
    }

    fun buildAndTransformRanges(input: List<String>, seeds: List<LongRange>): Set<LongRange> {
        var availableRanges = seeds.toSet()
        // initialize seeds since these are the only numbers that need to be mapped
        var mappedRanges = mutableSetOf<LongRange>()
        for (line in input) {
            if (line.contains(Regex("[0-9]"))) {
                // nums = [dest, source, range]
                val nums = line.split(" ").map { it.toLong() }
                val soureceRange = nums[1] until (nums[1] + nums[2])
                val targetRange = nums[0] until (nums[0] + nums[2])
                val tmpRanges = availableRanges.toMutableSet()
                for (originalRange in availableRanges) {
                    // check if (startA <= endB) and (endA >= startB)
                    if (soureceRange.first <= originalRange.last && soureceRange.last >= originalRange.first) {
                        // remove original and re-add as partitioned range
                        tmpRanges.remove(originalRange)
                        val res = splitRangesAndMap(originalRange, soureceRange, targetRange)
                        tmpRanges.addAll(res.first)
                        mappedRanges.addAll(res.second)
                    }
                }
                // all ranges and subranges that have not been mapped yet
                availableRanges = tmpRanges
            } else if (line.contains(Regex("[a-z]"))) {
                // keep not mapped ranges and add mapped ranges
                availableRanges = availableRanges.union(mappedRanges)
                mappedRanges = mutableSetOf()
            }
        }
        return mappedRanges.union(availableRanges).toSet()
    }

    fun execute(resourceFile: String): Pair<Long, Long> {
        val lines = this::class.java.getResourceAsStream(resourceFile)?.bufferedReader()?.readLines()!!
        // part 1

        val seedsPart1 = lines[0].substringAfter(": ").split(" ").map { it.toLong()..it.toLong() }
        val mappedRangesPart1 = buildAndTransformRanges(lines.subList(2 ,lines.size), seedsPart1)
        // Part 2
        val seedInputPart2 = lines[0].substringAfter(": ").split(" ").map { it.toLong() }
        // Pair(start , Range)
        val seedsPart2 = mutableListOf<LongRange>()
        var counter = 0
        while (counter < seedInputPart2.size) {
            val startNum = seedInputPart2[counter]
            seedsPart2.add(startNum until (startNum + seedInputPart2[counter + 1]))
            counter += 2
        }
        val mappedRangesPart2 = buildAndTransformRanges(lines.subList(2 ,lines.size), seedsPart2)
        var minPart1 = mappedRangesPart1.first().first
        for (r in mappedRangesPart1) {
            val currentStart = r.first
            if (currentStart < minPart1) {
                minPart1 = currentStart
            }
        }
        var minPart2 = mappedRangesPart2.first().first
        for (r in mappedRangesPart2) {
            val currentStart = r.first
            if (currentStart < minPart2) {
                minPart2 = currentStart
            }
        }
        return Pair(minPart1, minPart2)
    }
}