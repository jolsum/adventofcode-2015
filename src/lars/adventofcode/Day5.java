package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day5 {

	private static final Pattern THREE_VOWELS = Pattern.compile("[a,e,i,o,u,y].*[a,e,i,o,u,y].*[a,e,i,o,u,y]");
	private static final Pattern DOUBLE_LETTER = Pattern.compile("([a-z])\\1");

	public static void main(String[] args) throws IOException {

		System.out.println(isNice("ugknbfddgicrmopn"));
		System.out.println(isNice("aaa"));
		System.out.println(isNice("jchzalrnumimnmhp"));
		System.out.println(isNice("haegwjzuvuyypxyu"));
		System.out.println(isNice("dvszwmarrgswjxmb"));

		try (Stream<String> lines = Files.lines(Paths.get("input", "5.txt"))) {
			System.out.println(lines.filter(l -> isNice(l)).count());
		}

	}

	private static boolean isNice(final String in) {

		return THREE_VOWELS.matcher(in).find() && DOUBLE_LETTER.matcher(in).find() && !in.contains("ab")
				&& !in.contains("cd") && !in.contains("pq") && !in.contains("xy");
	}
}
