package lars.adventofcode;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;

public class Day24 {

	private static final int GROUPS = 3;

	private static int minGroupSize = Integer.MAX_VALUE;

	private static BigInteger minGroupQE = BigInteger.valueOf(Long.MAX_VALUE);
	private static int[] minQEGroup = null;

	public static void main(String[] args) throws IOException {

		int[] packages;
		try (Stream<String> lines = Files.lines(Paths.get("input", "24.txt"))) {
			packages = lines.mapToInt(Integer::parseInt).toArray();
		}

		int sum = IntStream.of(packages).sum();
		int groupSum = sum / GROUPS;

		findGroups(packages, groupSum, new int[0], 0, 0);

		System.out.println("Min QE: " + minGroupQE + ": " + Arrays.toString(minQEGroup));
	}

	private static void findGroups(int[] remainingPackages, int target, int[] currentGroup, int groupsFound, int index) {

		if (currentGroup.length > minGroupSize) {
			return;
		}

		final int currentSum = IntStream.of(currentGroup).sum();

		if (currentSum == target) {
			groupsFound++;

			if (groupsFound == GROUPS) {
				int groupSize = currentGroup.length;
				if (groupSize <= minGroupSize) {
					minGroupSize = groupSize;

					// Calculate QE
					BigInteger qe = BigInteger.valueOf(1);
					for (int i = 0; i < currentGroup.length; i++) {
						qe = qe.multiply(BigInteger.valueOf(currentGroup[i]));
					}

					if (qe.compareTo(minGroupQE) < 0) {
						minGroupQE = qe;
						minQEGroup = currentGroup;
						// System.out.println("min QE: " + minGroupQE + " " +
						// Arrays.toString(minQEGroup));
					}
				}
				return;
			}
			currentGroup = new int[0];
			index = 0;
		}
		else if (currentSum > target) {
			return;
		}

		for (int i = index; i < remainingPackages.length; i++) {
			int number = remainingPackages[i];

			int[] newRemaining = ArrayUtils.remove(remainingPackages, i);
			int[] newGroup = ArrayUtils.add(currentGroup, number);

			findGroups(newRemaining, target, newGroup, groupsFound, i);
		}

	}

}
