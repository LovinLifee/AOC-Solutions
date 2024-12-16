package net.avuna.aoc.y2024;

import org.togetherjava.aoc.core.annotations.AdventDay;
import org.togetherjava.aoc.core.math.Direction;
import org.togetherjava.aoc.core.math.matrix.Matrix;
import org.togetherjava.aoc.core.math.matrix.MatrixPosition;
import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;

import java.util.HashSet;
import java.util.Set;

@AdventDay(day = 6)
public class Day06 implements PuzzleSolution {

	private final Set<MatrixPosition> positions = new HashSet<>();

	@Override
	public Object part1(PuzzleInput puzzleInput) {
		Matrix<Character> matrix = puzzleInput.toCharMatrix();
		MatrixPosition position = matrix.stream().filter(e -> e.value().equals('^')).findFirst().orElseThrow().position();
		Direction direction = Direction.NORTH;
		while(matrix.inBounds(position)) {
			MatrixPosition nextPosition = position.move(direction, 1);
			if (matrix.inBounds(nextPosition) && matrix.get(nextPosition) == '#') {
				direction = direction.rotateRight();
			} else {
				position = nextPosition;
				positions.add(position);
			}
		}
		return positions.size();
	}

	@Override
	public Object part2(PuzzleInput puzzleInput) {
		Matrix<Character> matrix = puzzleInput.toCharMatrix();
		final int hardLimit = matrix.getRows() * matrix.getCols();
		MatrixPosition position = matrix.stream().filter(e -> e.value().equals('^')).findFirst().orElseThrow().position();
		Direction direction = Direction.NORTH;
		Set<MatrixPosition> distinctPositions = new HashSet<>();
		while(matrix.inBounds(position)) {
			MatrixPosition nextPosition = position.move(direction, 1);
			if (matrix.inBounds(nextPosition) && matrix.get(nextPosition) == '#') {
				direction = direction.rotateRight();
			} else {
				position = nextPosition;
				distinctPositions.add(position);
			}
		}
		return distinctPositions.size();
	}
}
