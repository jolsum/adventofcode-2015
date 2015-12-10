package lars.adventofcode.day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day2 {

	private static int calculatePaper(int x, int y, int z) {
		int a = x * y;
		int b = y * z;
		int c = x * z;
		int extra = Math.min(a, Math.min(b, c));

		int tot = 2 * a + 2 * b + 2 * c + extra;

		return tot;
	}

	private static int calculateRibbon(int x, int y, int z) {

		List<Integer> numbers = Arrays.asList(x, y, z);
		Collections.sort(numbers);

		return 2 * numbers.get(0) + 2 * numbers.get(1) + (x * y * z);

	}

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader(new File("input", "2.txt")));

		int paperTot = 0;
		int ribbonTot = 0;

		String line;
		while ((line = in.readLine()) != null) {

			String[] parts = line.split("x");
			int x = Integer.parseInt(parts[0]);
			int y = Integer.parseInt(parts[1]);
			int z = Integer.parseInt(parts[2]);

			paperTot += calculatePaper(x, y, z);
			ribbonTot += calculateRibbon(x, y, z);

		}

		System.out.println(paperTot);
		System.out.println(ribbonTot);
	}

}
