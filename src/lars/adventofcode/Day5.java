package lars.adventofcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class Day5 {

	private static final Pattern THREE_VOWELS = Pattern.compile("[a,e,i,o,u,y].*[a,e,i,o,u,y].*[a,e,i,o,u,y]");
	private static final Pattern DOUBLE_LETTER = Pattern.compile("([a-z])\\1");

	public static void main(String[] args) throws IOException {

		System.out.println(isNice("ugknbfddgicrmopn"));
		System.out.println(isNice("aaa"));
		System.out.println(isNice("jchzalrnumimnmhp"));
		System.out.println(isNice("haegwjzuvuyypxyu"));
		System.out.println(isNice("dvszwmarrgswjxmb"));

		try (BufferedReader in = new BufferedReader(new FileReader(new File("input", "5.txt")))) {

			int nice = 0;

			String line;
			while ((line = in.readLine()) != null) {

				boolean isNice = isNice(line);
				if (isNice) {
					nice++;
				}

			}

			System.out.println(nice);

		}

	}

	private static boolean isNice(final String in) {

		return THREE_VOWELS.matcher(in).find() && DOUBLE_LETTER.matcher(in).find() && !in.contains("ab")
				&& !in.contains("cd") && !in.contains("pq") && !in.contains("xy");
	}
}
