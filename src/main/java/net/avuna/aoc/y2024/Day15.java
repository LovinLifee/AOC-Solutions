package net.avuna.aoc.y2024;

import org.togetherjava.aoc.core.math.Direction;
import org.togetherjava.aoc.core.math.matrix.Matrix;
import org.togetherjava.aoc.core.math.matrix.MatrixPosition;
import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;

//TODO: Do day 15 pt. 2
public class Day15 implements PuzzleSolution {

	@Override
	public Object part1(PuzzleInput puzzleInput) {
		var matrix = puzzleInput.getClusters().getFirst().toCharMatrix();
		var moveLines = puzzleInput.getClusters().getLast().getLines();
		MatrixPosition position = matrix.stream().filter(e -> e.value() == '@').findFirst().map(Matrix.Entry::position).orElseThrow();
		for(String line : moveLines) {
			char[] moves = line.toCharArray();
			for(char move : moves) {
				Direction direction = switch(move) {
					case '^' -> Direction.NORTH;
					case 'v' -> Direction.SOUTH;
					case '<' -> Direction.WEST;
					case '>' -> Direction.EAST;
					default -> throw new IllegalStateException("Unexpected value: " + (int) move);
				};

				MatrixPosition newPosition = position.move(direction);

				//do nothing if the robot runs in to a wall
				if(matrix.get(newPosition) == '#') {
					continue;
				}

				var boxes = matrix.rayCastWhile(newPosition, direction, entry -> entry.value() == 'O');

				//do nothing if the last box can't be moved
				if(!boxes.isEmpty() && matrix.get(boxes.getLast().position().move(direction)) == '#') {
					continue;
				}

				//set the new head and remove the tail
				if(!boxes.isEmpty()) {
					matrix.set(boxes.getFirst().position(), '.');
					matrix.set(boxes.getLast().position().move(direction), 'O');
				}

				/*//update matrix with new position of player and boxes
				for(var box : boxes) {
					matrix.set(box.position(), '.');
					matrix.set(box.position().move(direction), 'O');
				}*/

				matrix.set(position, '.');
				matrix.set(newPosition, '@');
				position = newPosition;
			}
		}

		System.out.println(matrix);
		//sum all the positions
		return matrix.stream()
				.filter(entry -> entry.value() == 'O')
				.map(Matrix.Entry::position)
				.mapToLong(pos -> (pos.row() * 100L) + pos.col())
				.sum();
	}

	@Override
	public Object part2(PuzzleInput puzzleInput) {
		return null;
	}
}
