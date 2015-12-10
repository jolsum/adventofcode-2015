package lars.adventofcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Day3 {

	public static void main(String[] args) throws IOException {

		Set<String> visited = new HashSet<>();

		try (BufferedReader in = new BufferedReader(new FileReader(new File("input", "3.txt")))) {

			int x = 0;
			int y = 0;

			int c;
			while ((c = in.read()) != -1) {

				char ch = (char) c;

				switch (ch) {

				case '>':
					x++;
					break;

				case '<':
					x--;
					break;

				case '^':
					y++;
					break;

				case 'v':
					y--;
					break;

				}

				visited.add(x + "-" + y);
			}
		}

		System.out.println(visited.size() + 1);
	}
}
