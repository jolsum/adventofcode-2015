package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day23 {

	public static void main(String[] args) throws IOException {

		List<Operation> operations;
		try (Stream<String> lines = Files.lines(Paths.get("input", "23.txt"))) {
			operations = lines.map(l -> read(l)).collect(Collectors.toList());
		}

		AtomicInteger a = new AtomicInteger();
		AtomicInteger b = new AtomicInteger();

		int pos = 0;
		while (pos >= 0 && pos < operations.size()) {
			Operation operation = operations.get(pos);

			AtomicInteger register = null;
			if (operation.register != null) {
				register = (operation.register.equals("a") ? a : b);
			}

			switch (operation.command) {
				case "hlf":
					register.set(register.get() / 2);
					break;

				case "tpl":
					register.set(register.get() * 3);
					break;

				case "inc":
					register.incrementAndGet();
					break;

				case "jio":
					if (register.get() == 1) {
						pos += operation.value;
						continue;
					}
					break;

				case "jie":
					if (register.get() % 2 == 0) {
						pos += operation.value;
						continue;
					}
					break;

				case "jmp":
					pos += operation.value;
					continue;

				default:
					throw new IllegalArgumentException("Unknown command: " + operation.command);
			}

			pos++;
		}

		System.out.println("a: " + a + ", b: " + b);

	}

	private static Operation read(String line) {
		String[] parts = line.replace(",", "").split(" ");

		Operation o = new Operation();
		o.command = parts[0];

		String value = null;
		switch (parts[0]) {
			case "jmp offset":
			case "jie":
			case "jio":
				value = parts[2];
				o.value = (value.startsWith("-") ? -1 : 1) * Integer.parseInt(value.substring(1));

			case "hlf":
			case "tpl":
			case "inc":
				o.register = parts[1];
				break;

			case "jmp":
				value = parts[1];
				o.value = (value.startsWith("-") ? -1 : 1) * Integer.parseInt(value.substring(1));
		}

		return o;
	}

	private static class Operation {
		String command;
		String register;
		int value;

		@Override
		public String toString() {
			return command + (register == null ? "" : " " + register) + (value != 0 ? " " + value : "");
		}

	}

}
