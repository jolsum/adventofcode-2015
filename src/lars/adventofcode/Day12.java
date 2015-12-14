package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day12 {

	public static void main(String[] args) throws IOException {

		String input = new String(Files.readAllBytes(Paths.get("input", "12.txt")));

		System.out.println(sum(input));
	}

	private static int sum(String input) {
		Pattern p = Pattern.compile("-?\\d+");
		Matcher m = p.matcher(input);
		int sum = 0;
		while (m.find()) {
			sum += Integer.parseInt(m.group());
		}
		return sum;
	}

}
