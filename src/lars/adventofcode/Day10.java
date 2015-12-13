package lars.adventofcode;

public class Day10 {

	public static void main(String[] args) {
		String digits = "1321131112";

		for (int i = 0; i < 40; i++) {
			digits = lookAndSay(digits);
		}
		System.out.println(digits.length());
	}

	private static String lookAndSay(final String input) {

		StringBuilder output = new StringBuilder();

		char prev = input.charAt(0);
		int count = 1;
		for (int i = 1; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c == prev) {
				count++;
			} else {
				output.append(count);
				output.append(prev);
				prev = c;
				count = 1;
			}
		}

		output.append(count);
		output.append(prev);

		return output.toString();
	}

}
