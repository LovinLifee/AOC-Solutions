package net.avuna.aoc.y2024;

import org.togetherjava.aoc.core.annotations.AdventDay;
import org.togetherjava.aoc.core.math.Direction;
import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;

import java.util.List;

@AdventDay(day = 4)
public class Day04 implements PuzzleSolution {

	@Override
	public Object part1(PuzzleInput puzzleInput) {
		long count = 0;
		var xmas = List.of('X', 'M', 'A', 'S');
		var matrix = puzzleInput.toCharMatrix();
		for(var e : matrix.getEntries()) {
			for(Direction d : Direction.getAll()) {
				var ray = matrix.rayCast(e.position(), d,  4);
				if(ray.equals(xmas)) {
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public Object part2(PuzzleInput puzzleInput) {
		long count = 0;
		var matrix = puzzleInput.toCharMatrix();
		for(var indicie : matrix.getEntries()) {
			if(indicie.value() == 'A') {
				var topLeft = matrix.tryGet(indicie.position().move(Direction.NORTH_WEST));
				var topRight = matrix.tryGet(indicie.position().move(Direction.NORTH_EAST));
				var bottomLeft = matrix.tryGet(indicie.position().move(Direction.SOUTH_WEST));
				var bottomRight = matrix.tryGet(indicie.position().move(Direction.SOUTH_EAST));
				if(topLeft.isEmpty() || topRight.isEmpty() || bottomLeft.isEmpty() || bottomRight.isEmpty()) {
					continue;
				}
				if(((topLeft.get() == 'S' && bottomRight.get() == 'M') || (topLeft.get() == 'M' && bottomRight.get() == 'S')) && ((topRight.get() == 'S' && bottomLeft.get() == 'M') || (topRight.get() == 'M' && bottomLeft.get() == 'S'))) {
					count++;
				}
			}
		}
		return count;
	}
}
