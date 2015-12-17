package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day17 {

	static final int CAPACITY = 150;
	static int total = 0;

	public static void main(String[] args) throws IOException {

		List<Integer> containers;
		try (Stream<String> lines = Files.lines(Paths.get("input", "17.txt"))) {
			containers = lines.map(l -> Integer.parseInt(l)).sorted().collect(Collectors.toList());
		}

		combinations(containers, Collections.emptyList());
	}

	private static void combinations(List<Integer> remainingContainers, List<Integer> added) {

		int sum = added.stream().mapToInt(i -> i).sum();
		if (sum > CAPACITY) {
			return;
		}

		if (sum == CAPACITY) {
			// System.out.println(added + " " + remainingContainers);
			total++;
			return;
		}

		for (int i = 0; i < remainingContainers.size(); i++) {
			List<Integer> newRemaining = new ArrayList<>(remainingContainers);
			newRemaining.remove(i);

			List<Integer> newAdded = new ArrayList<>(added);
			newAdded.add(remainingContainers.get(i));

			combinations(newRemaining, newAdded);
		}
	}

}
