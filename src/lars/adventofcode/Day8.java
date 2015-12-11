package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day8 {

	private static int totChars = 0;
	private static int totMem = 0;
	private static int totEscaped = 0;

	public static void main(String[] args) throws IOException {

		try (Stream<String> lines = Files.lines(Paths.get("input", "8.txt"))) {
			lines.forEach(l -> {
				handleLine(l);
				handleLine2(l);
			});

		}

		System.out.println(totChars + " - " + totMem + " = " + (totChars - totMem));
		System.out.println(totEscaped + " - " + totChars + " = " + (totEscaped - totChars));

	}

	private static void handleLine(final String line) {
		String newLine = line.substring(1, line.length() - 1);
		newLine = newLine.replaceAll("\\\\x[0-9a-f]{2}", "¤"); // \x27
		newLine = newLine.replace("\\\"", "¤"); // \"
		newLine = newLine.replace("\\\\", "¤"); // \\

		totChars += line.length();
		totMem += newLine.length();
	}

	private static void handleLine2(final String line) {
		String newLine = line;
		newLine = newLine.replace("\\", "\\\\"); // \ -> \\
		newLine = newLine.replace("\"", "\\\""); // " -> \"
		newLine = "\"" + newLine + "\"";

		totEscaped += newLine.length();
	}

}
