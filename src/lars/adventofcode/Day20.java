package lars.adventofcode;

public class Day20 {

	public static void main(String[] args) {

		for (int house = 1;; house++) {
			int presents = 0;
			for (int elf = 1; elf <= house; elf++) {
				// Part 1
				if (house % elf == 0) {
					presents += elf * 10;
				}
				// Part 2
				if (house <= elf * 50 && house % elf == 0) {
					presents += elf * 11;
				}
			}

			if (house % 10000 == 0) {
				System.out.println("House " + house + " got " + presents + " presents");
			}

			if (presents >= 29000000) {
				System.out.println("House " + house + " got " + presents + " presents");
				break;
			}
		}

	}

}
