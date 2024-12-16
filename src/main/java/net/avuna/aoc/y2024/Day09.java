package net.avuna.aoc.y2024;

import org.togetherjava.aoc.core.annotations.AdventDay;
import org.togetherjava.aoc.core.puzzle.PuzzleInput;
import org.togetherjava.aoc.core.puzzle.PuzzleSolution;

import java.util.*;

//TODO: Make this work 😂
@AdventDay(day = 9)
public class Day09 implements PuzzleSolution {

	@Override
	public Object part1(PuzzleInput puzzleInput) {
		long count = 0L;
		String raw = "2333133121414131402";
		char[] chars = raw.toCharArray();
		List<Segment> segments = new ArrayList<>();
		for(int i = 0; i < chars.length; i++) {
			Segment segment;
			int space = Character.getNumericValue(chars[i]);
			if(i % 2 == 0) {
				segment = new FileBlock(i / 2, space);
			} else {
				segment = new FreeSpace(space);
			}
			segments.add(segment);
		}

		StringBuilder builder = new StringBuilder();
		segments.forEach(builder::append);
		System.out.println(builder);

		while(hasFreeSpaceBetweenFileBlocks(segments)) {
			for(int i = segments.size(); i --> 0;) {
				if(segments.get(i) instanceof FileBlock file) {
					int freeSpaceIndex = getIndexOfFreeBlockThatFits(segments, file);
					if(freeSpaceIndex > 0) {
						var freeSpace = segments.get(freeSpaceIndex);
						segments.set(freeSpaceIndex, file);
						segments.set(i, freeSpace);
					}
				}
			}
		}

		StringBuilder builder2 = new StringBuilder();
		segments.forEach(builder2::append);
		System.out.println(builder2);

		int index = 0;
		for(int i = 0; i < segments.size(); i++) {
			Segment segment = segments.get(i);
			if(segment instanceof FileBlock file) {
				count += ((long) index++ * file.id());
			}
		}
		return count;
	}

	private int getIndexOfFreeBlockThatFits(List<Segment> segments, FileBlock block) {
		for(int i = 0; i < segments.size(); i++) {
			if(segments.get(i) instanceof FreeSpace free) {
				if(free.size() >= block.space()) {
					return i;
				}
			}
		}
		return -1;
	}

	private static boolean hasFreeSpaceBetweenFileBlocks(List<Segment> segments) {
		boolean foundFileBlock = false;
		for (Segment segment : segments) {
			if (segment instanceof FileBlock) {
				if (foundFileBlock) {
					// If we already found a FileBlock before and encounter another, return false if there's no free space in between
					return false;
				}
				foundFileBlock = true;
			} else if (segment instanceof FreeSpace) {
				foundFileBlock = false; // Reset to false if FreeSpace is found
			}
		}
		return foundFileBlock;
	}


	@Override
	public Object part2(PuzzleInput puzzleInput) {
		return null;
	}

	record FileBlock(int id, int length) implements Segment {

		@Override
		public int space() {
			return length;
		}

		@Override
		public String toString() {
			return String.valueOf(id).repeat(length);
		}
	}

	record FreeSpace(int size) implements Segment{

		@Override
		public int space() {
			return size;
		}

		@Override
		public String toString() {
			return ".".repeat(size);
		}
	}

	interface Segment {
		int space();
	}
}
