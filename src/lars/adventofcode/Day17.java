package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day17 {

	static final int CAPACITY = 150;

	static Collection<List<Integer>> combinations = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		List<Integer> containers;
		try (Stream<String> lines = Files.lines(Paths.get("input", "17.txt"))) {
			containers = lines.map(l -> Integer.parseInt(l)).sorted().collect(Collectors.toList());
		}

		combinations(containers, Collections.emptyList(), 0);

		System.out.println("Total combinations: " + combinations.size());

		int minSize = combinations.stream().map(l -> l.size()).mapToInt(i -> i).min().getAsInt();
		long count = combinations.stream().filter(l -> l.size() == minSize).count();

		System.out.println(count + " combinations with size " + minSize);
	}

	private static void combinations(List<Integer> remainingContainers, List<Integer> added, int pos) {

		int sum = added.stream().mapToInt(i -> i).sum();

		if (sum == CAPACITY) {
			combinations.add(added);
			return;
		}

		for (int i = pos; i < remainingContainers.size(); i++) {
			List<Integer> newRemaining = new ArrayList<>(remainingContainers);
			newRemaining.remove(i);

			List<Integer> newAdded = new ArrayList<>(added);
			newAdded.add(remainingContainers.get(i));

			combinations(newRemaining, newAdded, i);
		}
	}

}
