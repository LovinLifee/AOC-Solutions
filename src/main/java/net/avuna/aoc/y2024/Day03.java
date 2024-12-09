package net.avuna.aoc.y2024;

import org.togetherjava.aoc.core.Regex;
import org.togetherjava.aoc.core.annotations.AdventDay;
import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;

@AdventDay(day = 3)
public class Day03 implements PuzzleSolution {

	@Override
	public Object part1(PuzzleInput puzzleInput) {
		long result = 0L;
		final String multiplicationFunctions = "mul\\((\\d+),(\\d+)\\)";
		for(var matchResult : Regex.allMatches(multiplicationFunctions, puzzleInput.rawInput())) {
			long leftOperand = Long.parseLong(matchResult.group(1));
			long rightOperand = Long.parseLong(matchResult.group(2));
			result += leftOperand * rightOperand;
		}
		return result;
	}

	@Override
	public Object part2(PuzzleInput puzzleInput) {
		long count = 0;
		String allValidFunctions = "mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)";
		boolean doIt = true;
		for(var result : Regex.allMatches(allValidFunctions, puzzleInput.rawInput())) {
			String functionName = result.group();
			functionName = functionName.substring(0, functionName.indexOf("("));
			switch(functionName) {
				case "do" -> doIt = true;
				case "don't" -> doIt = false;
				case "mul" -> {
					if(doIt) {
						long leftOperand = Long.parseLong(result.group(1));
						long rightOperand = Long.parseLong(result.group(2));
						count += leftOperand * rightOperand;
					}
				}
			};
		}
		return count;
	}
}
