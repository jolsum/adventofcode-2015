package lars.adventofcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Day3Part2 {

	public static void main(String[] args) throws IOException {

		Set<String> visited = new HashSet<>();
		visited.add("0-0");

		try (BufferedReader in = new BufferedReader(new FileReader(new File("input", "3.txt")))) {

			Position santa = new Position();
			Position robot = new Position();

			Position current = santa;

			int c;
			while ((c = in.read()) != -1) {

				char ch = (char) c;

				switch (ch) {

				case '>':
					current.x++;
					break;

				case '<':
					current.x--;
					break;

				case '^':
					current.y++;
					break;

				case 'v':
					current.y--;
					break;

				}

				visited.add(current.x + "-" + current.y);

				current = (current == santa ? robot : santa);
			}
		}

		System.out.println(visited.size());
	}

	private static class Position {
		int x = 0;
		int y = 0;
	}
}
