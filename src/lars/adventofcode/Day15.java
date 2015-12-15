package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Day15 {

	private static List<Ingredient> ingredients = new ArrayList<>();

	private static int max = Integer.MIN_VALUE;
	private static Map<Ingredient, Integer> maxCounts;

	public static void main(String[] args) throws IOException {

		try (Stream<String> lines = Files.lines(Paths.get("input", "15.txt"))) {
			lines.forEach(l -> handleLine(l));
		}

		calculate(new HashMap<>(), ingredients, 100);

		System.out.println(max + " " + maxCounts);
	}

	private static void calculate(Map<Ingredient, Integer> added, List<Ingredient> ingredients, int count) {

		if (ingredients.size() == 1) {
			added.put(ingredients.get(0), count);
			int capacity = Math.max(0, added.keySet().stream().mapToInt(i -> i.capacity * added.get(i)).sum());
			int durability = Math.max(0, added.keySet().stream().mapToInt(i -> i.durability * added.get(i)).sum());
			int flavor = Math.max(0, added.keySet().stream().mapToInt(i -> i.flavor * added.get(i)).sum());
			int texture = Math.max(0, added.keySet().stream().mapToInt(i -> i.texture * added.get(i)).sum());
			int calories = Math.max(0, added.keySet().stream().mapToInt(i -> i.calories * added.get(i)).sum());

			int res = capacity * durability * flavor * texture;

			if (res > max /* && calories == 500 */) {
				max = res;
				maxCounts = new HashMap<>(added);
			}

			return;
		}

		Ingredient ingredient = ingredients.remove(0);
		for (int i = 0; i <= count; i++) {
			added.put(ingredient, i);
			calculate(added, new ArrayList<>(ingredients), (count - i));
		}

	}

	private static void handleLine(final String line) {

		String[] parts = line.replace(",", "").split(" ");

		Ingredient i = new Ingredient();
		i.name = parts[0];
		i.capacity = Integer.parseInt(parts[2]);
		i.durability = Integer.parseInt(parts[4]);
		i.flavor = Integer.parseInt(parts[6]);
		i.texture = Integer.parseInt(parts[8]);
		i.calories = Integer.parseInt(parts[10]);

		ingredients.add(i);
	}

	static class Ingredient {

		String name;

		int capacity;
		int durability;
		int flavor;
		int texture;
		int calories;

		@Override
		public String toString() {
			return name;
		}
	}

}
