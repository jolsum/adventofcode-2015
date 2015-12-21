package lars.adventofcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day19 {

	private static final List<Replacement> replacements = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		String start = read(new File("input", "19.txt"));

		findMolecules(start, 0);
	}

	private static void findMolecules(String start, int steps) {

		for (Replacement r : replacements) {
			List<String> newMolecules = doReplacements(start, r);
			for (String newMolecule : newMolecules) {
				if (newMolecule.equals("e")) {
					System.out.println("Found transition in " + (steps + 1) + " steps");
					System.exit(0);
				}
				findMolecules(newMolecule, steps + 1);
			}
		}

	}

	private static List<String> doReplacements(String start, Replacement r) {

		List<String> newMolecules = new ArrayList<>();

		int pos = 0;
		while ((pos = start.indexOf(r.to, pos)) != -1) {
			String newMolecule = start.substring(0, pos) + r.from + start.substring(pos + r.to.length());
			newMolecules.add(newMolecule);
			pos++;
		}

		return newMolecules;
	}

	private static String read(File file) throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {

			String line;
			while ((line = in.readLine()) != null) {
				int pos = line.indexOf(" => ");
				if (pos != -1) {
					Replacement r = new Replacement();
					r.from = line.substring(0, pos);
					r.to = line.substring(pos + 4);
					replacements.add(r);

				} else if (line.length() != 0) {
					return line;
				}
			}

		}
		throw new IOException("No start string found");
	}

	private static final class Replacement {

		String from;
		String to;

	}

}
