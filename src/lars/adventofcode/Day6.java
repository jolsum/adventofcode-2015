package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day6 {

	private static enum Operation {
		TOGGLE {
			@Override
			int apply(int value) {
				return value + 2;
			}
		},
		ON {
			@Override
			int apply(int value) {
				return value + 1;
			}
		},
		OFF {
			@Override
			int apply(int value) {
				return Math.max(0, value - 1);
			}
		};

		abstract int apply(int value);
	}

	private static int[][] grid = new int[1000][1000];

	public static void main(String[] args) throws IOException {

		try (Stream<String> lines = Files.lines(Paths.get("input", "6.txt"))) {
			lines.forEach(l -> handleLine(l));

			int sum = 0;
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					sum += grid[i][j];
				}
			}
			System.out.println(sum);
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
