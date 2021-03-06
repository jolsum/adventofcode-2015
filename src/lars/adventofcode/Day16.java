package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Day16 {

	private static final Map<String, Map<String, Integer>> aunts = new HashMap<>();

	public static void main(String[] args) throws IOException {

		try (Stream<String> lines = Files.lines(Paths.get("input", "16.txt"))) {
			lines.forEach(l -> handleLine(l));
		}

		Map<String, Integer> properties = new HashMap<>();
		properties.put("children", 3);
		properties.put("cats", 7);
		properties.put("samoyeds", 2);
		properties.put("pomeranians", 3);
		properties.put("akitas", 0);
		properties.put("vizslas", 0);
		properties.put("goldfish", 5);
		properties.put("trees", 3);
		properties.put("cars", 2);
		properties.put("perfumes", 1);

		String aunt = aunts.keySet().stream().filter(s -> matches(properties, aunts.get(s))).findAny().get();

		System.out.println(aunt);
	}

	private static boolean matches(Map<String, Integer> properties, Map<String, Integer> props) {
		for (String prop : props.keySet()) {
			int value = properties.get(prop);
			int auntValue = props.get(prop);

			// Part 2
			if (prop.equals("cats") || prop.equals("trees")) {
				if (auntValue > value) {
					return false;
				}
			} else if (prop.equals("pomeranians") || prop.equals("goldfish")) {
				if (auntValue < value) {
					return false;
				}
			} else

			// Part 1
			if (value != auntValue) {
				return false;
			}
		}
		return true;
	}

	private static void handleLine(final String line) {

		int pos = line.indexOf(":");
		String name = line.substring(0, pos);

		Map<String, Integer> properties = new HashMap<>();

		String[] props = line.substring(pos + 1).replace(" ", "").split(",");
		for (String prop : props) {
			pos = prop.indexOf(":");
			properties.put(prop.substring(0, pos), Integer.parseInt(prop.substring(pos + 1)));
		}

		aunts.put(name, properties);
	}

}
