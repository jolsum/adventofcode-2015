package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day14 {

	private static final int TOTAL_TIME = 2503;

	private static int longest = Integer.MIN_VALUE;
	private static String winner;

	public static void main(String[] args) throws IOException {

		try (Stream<String> lines = Files.lines(Paths.get("input", "14.txt"))) {
			lines.forEach(l -> handleLine(l));
		}

		System.out.println("The winner is " + winner + " who ran " + longest);
	}

	private static void handleLine(final String line) {
		String[] parts = line.substring(0, line.length() - 1).split(" ");

		String raindeer = parts[0];
		int speed = Integer.parseInt(parts[3]);
		int runTime = Integer.parseInt(parts[6]);
		int restTime = Integer.parseInt(parts[13]);

		int totTime = (runTime + restTime);
		int totalDistance = ((TOTAL_TIME / totTime) * runTime + Math.min(runTime, TOTAL_TIME % totTime)) * speed;

		if (totalDistance > longest) {
			longest = totalDistance;
			winner = raindeer;
		}

	}

}
