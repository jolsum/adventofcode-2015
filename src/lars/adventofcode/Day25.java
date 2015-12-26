package lars.adventofcode;

public class Day25 {

	public static void main(String[] args) {

		int num = findNumber(2978, 3083);
		System.out.println(num + ": " + generateCode(num));
	}

	private static int findNumber(int row, int column) {

		int number = 1;
		for (int i = 1; i < row; i++) {
			number += i;
		}

		for (int i = 1; i < column; i++) {
			number += row + i;
		}

		return number;
	}

	private static long generateCode(int number) {

		long num = 20151125;
		for (int i = 2; i <= number; i++) {
			num = (num * 252533) % 33554393;
		}

		return num;
	}

}
