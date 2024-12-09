package net.avuna.aoc.y2024;

import org.togetherjava.aoc.core.annotations.AdventDay;
import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;

import java.util.ArrayList;
import java.util.List;

@AdventDay(day = 2)
public class Day02 implements PuzzleSolution {

	@Override
	public Object part1(PuzzleInput puzzleInput) {
		return puzzleInput.parseNumbers().stream()
				.filter(this::isValidReport)
				.count();
	}

	@Override
	public Object part2(PuzzleInput puzzleInput) {
		return puzzleInput.parseNumbers().stream()
				.filter(this::areAnyReportPermutationsValid)
				.count();
	}

	private boolean areAnyReportPermutationsValid(List<Long> report) {
		for(int i = 0; i < report.size(); i++) {
			List<Long> listCopy = new ArrayList<>(report);
			listCopy.remove(i);
			if(isValidReport(listCopy)) {
				return true;
			}
		}
		return false;
	}

	private boolean isValidReport(List<Long> report) {
		long previous = report.getFirst();
		DirectionState directionState = DirectionState.UNKNOWN;
		for(int i = 1; i < report.size(); i++) {
			long current = report.get(i);
			DirectionState currentDirectionState = DirectionState.getState(previous, current);
			if(directionState != DirectionState.UNKNOWN && directionState != currentDirectionState) {
				return false;
			}
			long difference = Math.abs(current - previous);
			if(difference < 1 || difference > 3) {
				return false;
			}
			directionState = currentDirectionState;
			previous = current;
		}
		return true;
	}

	enum DirectionState {

		UNKNOWN, ASCENDING, DESCENDING;

		static DirectionState getState(long a, long b) {
			if(a < b) {
				return ASCENDING;
			}
			if(a > b) {
				return DESCENDING;
			}
			return UNKNOWN;
		}
	}
}
