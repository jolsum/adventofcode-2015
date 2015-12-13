package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.google.common.collect.Collections2;

public class Day13 {

	private static Map<String, Person> persons = new HashMap<>();

	private static int shortest = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		try (Stream<String> lines = Files.lines(Paths.get("input", "13.txt"))) {
			lines.forEach(l -> handleLine(l));
		}

		// Part two
		// getPerson("Lars");

		Collection<List<Person>> permutations = Collections2.permutations(persons.values());

		int max = Integer.MIN_VALUE;
		for (List<Person> p : permutations) {
			int gain = testSeating(p);
			if (gain > max) {
				max = gain;
			}
		}
		System.out.println(max);
	}

	private static void handleLine(final String line) {
		String[] parts = line.substring(0, line.length() - 1).split(" ");

		Person a = getPerson(parts[0]);
		int factor = (parts[2].equals("gain") ? 1 : -1);
		int value = Integer.parseInt(parts[3]) * factor;
		Person b = getPerson(parts[10]);

		a.gains.put(b, value);
	}

	private static int testSeating(List<Person> persons) {

		int sum = 0;

		Person left = persons.get(persons.size() - 1);
		for (int i = 0; i < persons.size(); i++) {
			Person right = persons.get(i);

			Integer ab = left.gains.get(right);
			Integer ba = right.gains.get(left);

			if (ab == null) {
				ab = 0;
			}
			if (ba == null) {
				ba = 0;
			}

			sum += ab;
			sum += ba;

			left = right;
		}

		return sum;
	}

	private static Person getPerson(String name) {
		Person person = persons.get(name);
		if (person == null) {
			person = new Person();
			person.name = name;
			persons.put(name, person);
		}
		return person;
	}

	private static final class Person {

		String name;
		final Map<Person, Integer> gains = new HashMap<>();

		@Override
		public String toString() {
			return name;
		}

	}
}
