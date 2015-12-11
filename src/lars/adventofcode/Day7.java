package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Day7 {

	private static Map<String, Wire> wires = new TreeMap<>();
	private static int MASK = 0xFFFF;

	public static void main(String[] args) throws IOException {

		try (Stream<String> lines = Files.lines(Paths.get("input", "7.txt"))) {
			lines.forEach(l -> handleLine(l));
			wires.forEach((s, w) -> System.out.println(s + ":" + w));
		}

		System.out.println(wires.get("a"));

	}

	private static void handleLine(String line) {
		String[] lineParts = line.split(" -> ");
		String command = lineParts[0];
		String wireName = lineParts[1];

		Wire wire = getWire(wireName);

		final String[] parts = command.split(" ");
		if (parts.length == 1) {
			wire.source = getSource(parts[0]);
		} else if (parts.length == 2 && parts[0].equals("NOT")) {
			wire.source = () -> ~getSource(parts[1]).get() & MASK;
		} else if (parts.length == 3) {
			Supplier<Integer> s1 = getSource(parts[0]);
			Supplier<Integer> s2 = getSource(parts[2]);

			if (parts[1].equals("AND")) {
				wire.source = () -> (s1.get() & s2.get()) & MASK;
			} else if (parts[1].equals("OR")) {
				wire.source = () -> (s1.get() | s2.get()) & MASK;
			} else if (parts[1].equals("RSHIFT")) {
				wire.source = () -> (s1.get() >> s2.get()) & MASK;
			} else if (parts[1].equals("LSHIFT")) {
				wire.source = () -> (s1.get() << s2.get()) & MASK;
			}
		}

		if (wire.source == null) {
			throw new RuntimeException("Unknown command: " + command);
		}
	}

	private static Supplier<Integer> getSource(String value) {
		try {
			int i = Integer.parseInt(value);
			return () -> i;
		} catch (NumberFormatException e) {
			return getWire(value);
		}
	}

	private static Wire getWire(String name) {
		Wire wire = wires.get(name);
		if (wire == null) {
			wire = new Wire();
			wires.put(name, wire);
		}
		return wire;
	}

	private static class Wire implements Supplier<Integer> {

		Supplier<Integer> source;
		Integer value;

		@Override
		public Integer get() {
			if (value == null) {
				value = source.get();
			}
			return value;
		}

		@Override
		public String toString() {
			return Integer.toString(get());
		}

	}

}
