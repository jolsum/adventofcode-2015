package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day6 {

	private static enum Operation {
		TOGGLE {
			@Override
			boolean apply(boolean value) {
				return !value;
			}
		},
		ON {
			@Override
			boolean apply(boolean value) {
				return true;
			}
		},
		OFF {
			@Override
			boolean apply(boolean value) {
				return false;
			}
		};

		abstract boolean apply(boolean value);
	}

	private static boolean[][] grid = new boolean[1000][1000];

	public static void main(String[] args) throws IOException {

		try (Stream<String> lines = Files.lines(Paths.get("input", "6.txt"))) {
			lines.forEach(l -> handleLine(l));

			int count = 0;
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					if (grid[i][j]) {
						count++;
					}
				}
			}
			System.out.println(count);
		}

	}

	private static void handleLine(String line) {
		String[] parts = line.split(" ");
		if (parts[0].equals("toggle")) {
			handleInstruction(Operation.TOGGLE, parts[1], parts[3]);
		} else {
			Operation operation = (parts[1].equals("on") ? Operation.ON : Operation.OFF);
			handleInstruction(operation, parts[2], parts[4]);
		}
	}

	private static void handleInstruction(Operation operation, String from, String to) {
		String[] f = from.split(",");
		int fromX = Integer.parseInt(f[0]);
		int fromY = Integer.parseInt(f[1]);

		String[] t = to.split(",");
		int toX = Integer.parseInt(t[0]);
		int toY = Integer.parseInt(t[1]);

		for (int x = fromX; x <= toX; x++) {
			for (int y = fromY; y <= toY; y++) {
				grid[x][y] = operation.apply(grid[x][y]);
			}
		}

	}

}
