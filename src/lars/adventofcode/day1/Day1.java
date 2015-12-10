package lars.adventofcode.day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day1 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader(new File("input", "1.txt")));

		int floor = 0;
		int pos = 1;

		int c;
		while ((c = in.read()) != -1) {

			char ch = (char) c;

			if (ch == ')') {
				floor--;
			} else {
				floor++;
			}

			if (floor == -1) {
				System.out.println(pos);
				break;
			}

			pos++;
		}

	}

}
