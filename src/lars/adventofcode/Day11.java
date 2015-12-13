package lars.adventofcode;

import java.util.regex.Pattern;

public class Day11 {

	private static final Pattern IOU = Pattern.compile("[iol]");
	private static final int A = (int) 'a';
	private static final int Z = (int) 'z';

	public static void main(String[] args) {

		String password = "hepxcrrq";
		while (true) {
			password = next(password);
			if (isValid(password)) {
				break;
			}
		}
		System.out.println(password);
	}

	public static String next(String string) {

		char[] chars = string.toCharArray();

		for (int i = chars.length - 1; i >= 0; i--) {
			int c = (int) chars[i] + 1;
			if (c > Z) {
				chars[i] = A;
			} else {
				chars[i] = (char) c;
				break;
			}
		}

		return new String(chars);
	}

	public static boolean isValid(String password) {
		if (!containsStraight(password)) {
			return false;
		}
		if (IOU.matcher(password).find()) {
			return false;
		}
		if (!contains2Pairs(password)) {
			return false;
		}
		return true;
	}

	public static boolean containsStraight(String password) {

		char prev = ' ';
		int count = 1;
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if ((int) c == (int) prev + 1) {
				count++;
			} else {
				count = 1;
			}
			if (count == 3) {
				return true;
			}
			prev = c;
		}

		return false;
	}

	public static boolean contains2Pairs(String password) {

		char pair1 = ' ';

		char prev = ' ';
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (c == prev) {
				if (pair1 == ' ') {
					pair1 = c;
				}
				if (pair1 != c) {
					return true;
				}
			}
			prev = c;
		}

		return false;

	}

}
