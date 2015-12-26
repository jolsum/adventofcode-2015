package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

public class Day5 {

	private static final Pattern THREE_VOWELS = Pattern.compile("[aeiou].*[aeiou].*[aeiou]");
	private static final Pattern DOUBLE_LETTER = Pattern.compile("([a-z])\\1");

	private static final Pattern DOUBLE_LETTER_TWICE = Pattern.compile("([a-z]{2}).*\\1");
	private static final Pattern ONE_LETTER_REPEAT = Pattern.compile("([a-z])[a-z]\\1");

	public static void main(String[] args) throws IOException {

		List<String> lines = Files.readAllLines(Paths.get("input", "5.txt"));

		System.out.println(lines.stream().filter(l -> isNice(l)).count());
		System.out.println(lines.stream().filter(l -> isNice2(l)).count());

	}

	private static boolean isNice(final String in) {

		return THREE_VOWELS.matcher(in).find() && DOUBLE_LETTER.matcher(in).find() && !in.contains("ab") && !in.contains("cd") && !in.contains("pq") && !in.contains("xy");
	}

	private static boolean isNice2(final String in) {
		return DOUBLE_LETTER_TWICE.matcher(in).find() && ONE_LETTER_REPEAT.matcher(in).find();
	}
}
