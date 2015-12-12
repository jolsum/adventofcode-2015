package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Stream;

public class Day9 {

	private static Map<String, City> cities = new HashMap<>();

	private static int shortest = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		try (Stream<String> lines = Files.lines(Paths.get("input", "9.txt"))) {
			lines.forEach(l -> handleLine(l));
		}

		cities.forEach((n, c) -> findShortest(c, cities.values(), 0));

		System.out.println(shortest);
	}

	private static void findShortest(final City from, Collection<City> remaining, final int distance) {

		remaining = new HashSet<City>(remaining);
		remaining.remove(from);

		if (remaining.isEmpty()) {
			shortest = Math.min(distance, shortest);
			return;
		}

		for (City to : from.neighbors.keySet()) {

			if (!remaining.contains(to)) {
				continue;
			}

			findShortest(to, remaining, distance + from.neighbors.get(to));
		}

	}

	private static void handleLine(final String line) {
		String[] parts = line.split(" ");

		City from = getCity(parts[0]);
		City to = getCity(parts[2]);
		Integer distance = Integer.parseInt(parts[4]);

		from.neighbors.put(to, distance);
		to.neighbors.put(from, distance);

	}

	private static City getCity(String name) {
		City city = cities.get(name);
		if (city == null) {
			city = new City();
			cities.put(name, city);
		}
		return city;
	}

	private static final class City {

		final Map<City, Integer> neighbors = new HashMap<>();

	}
}
