package net.avuna.aoc.y2024;

import org.togetherjava.aoc.core.annotations.AdventDay;
import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;
import org.togetherjava.aoc.core.utils.Counter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@AdventDay(day = 1)
public class Day01 implements PuzzleSolution {

	@Override
	public Object part1(PuzzleInput puzzleInput) {
		List<Long> leftSide = new ArrayList<>();
		List<Long> rightSide = new ArrayList<>();
		puzzleInput.parseNumbers().forEach(pair -> {
			leftSide.add(pair.get(0));
			rightSide.add(pair.get(1));
		});
		Collections.sort(leftSide);
		Collections.sort(rightSide);
		return IntStream.range(0, leftSide.size())
				.mapToLong(i -> Math.abs(leftSide.get(i) - rightSide
						.get(i))).sum();
	}

	@Override
	public Object part2(PuzzleInput puzzleInput) {
		List<Long> leftSide = new ArrayList<>();
		Counter<Long> rightSideCounter = new Counter<>();
		puzzleInput.parseNumbers().forEach(pair -> {
			leftSide.add(pair.get(0));
			rightSideCounter.increment(pair.get(1));
		});
		return leftSide.stream().mapToLong(left -> left * rightSideCounter.getCount(left))
				.sum();
	}
}
