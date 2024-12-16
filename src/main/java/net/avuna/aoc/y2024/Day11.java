package net.avuna.aoc.y2024;

import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;

import java.util.*;

//TODO: Finish pt 2. add memoization and BigInteger
public class Day11 implements PuzzleSolution {

	@Override
	public Object part1(PuzzleInput puzzleInput) {
		List<Long> stones = puzzleInput.parseNumbers().stream().flatMap(Collection::stream).toList();
		for(int i = 0; i < 25; i++) {
			stones = blink(stones, Map.of());
		}
		return stones.size();
	}

	@Override
	public Object part2(PuzzleInput puzzleInput) {
		return null;
	}

	private List<Long> blink(List<Long> stones, Map<Long, List<Long>> memo) {
		List<Long> updatedStones = new ArrayList<>();
		for (long stone : stones) {
			if (memo.containsKey(stone)) {
				updatedStones.addAll(memo.get(stone));
			} else {
				List<Long> result = new ArrayList<>();
				if (stone == 0) {
					result.add(1L);
				} else if (String.valueOf(stone).length() % 2 == 0) {
					String stoneAsString = String.valueOf(stone);
					result.add(Long.parseLong(stoneAsString.substring(0, stoneAsString.length() / 2)));
					result.add(Long.parseLong(stoneAsString.substring(stoneAsString.length() / 2)));
				} else {
					result.add(stone * 2024L);
				}
				memo.put(stone, result);
				updatedStones.addAll(result);
			}
		}
		return updatedStones;
	}
}
