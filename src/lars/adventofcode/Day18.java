package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day18 {

	public static void main(String[] args) throws IOException {

		boolean[][] matrix = read("18.txt");

		// System.out.println("Inital state:");
		// print(matrix);

		// System.out.println(newValue(matrix, 4, 1));

		int steps = 100;
		for (int i = 0; i < steps; i++) {
			// System.out.println("\nAfter step " + (i + 1));
			matrix = step(matrix);
			// print(matrix);
		}

		System.out.println(countLightsOn(matrix));
	}

	private static boolean[][] read(String file) throws IOException {
		boolean[][] matrix = null;
		try (Stream<String> linesStream = Files.lines(Paths.get("input", file))) {
			List<String> lines = linesStream.collect(Collectors.toList());

			for (int i = 0; i < lines.size(); i++) {
				String line = lines.get(i);

				for (int j = 0; j < line.length(); j++) {
					if (matrix == null) {
						matrix = new boolean[lines.size()][line.length()];
					}
					matrix[i][j] = line.charAt(j) == '#';
				}
			}
		}
		return matrix;
	}

	private static boolean[][] step(boolean[][] in) {

		boolean[][] out = new boolean[in.length][in[0].length];

		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < in[i].length; j++) {
				out[i][j] = newValue(in, i, j);
			}
		}

		return out;
	}

	private static boolean newValue(boolean matrix[][], int x, int y) {

		int fromX = Math.max(0, x - 1);
		int toX = Math.min(x + 1, matrix.length - 1);

		int fromY = Math.max(0, y - 1);
		int toY = Math.min(y + 1, matrix[x].length - 1);

		int neighborsOn = 0;
		for (int i = fromX; i <= toX; i++) {
			for (int j = fromY; j <= toY; j++) {
				if (i == x && j == y) {
					continue;
				}
				neighborsOn += (matrix[i][j] ? 1 : 0);
			}
		}

		boolean currentOn = matrix[x][y];

		return (currentOn && neighborsOn == 2 || neighborsOn == 3) || (!currentOn && neighborsOn == 3);
	}

	private static void print(boolean[][] in) {

		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < in[i].length; j++) {
				System.out.print((in[i][j] ? '#' : '.'));
			}
			System.out.println();
		}

	}

	private static int countLightsOn(boolean[][] in) {
		int count = 0;
		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < in[i].length; j++) {
				count += (in[i][j] ? 1 : 0);
			}
		}
		return count;
	}

}
