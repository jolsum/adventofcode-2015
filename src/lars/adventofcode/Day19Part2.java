package lars.adventofcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day19Part2 {

	private static final Set<String> all = new HashSet<>();
	private static final List<Replacement> replacements = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		String medicine = read(new File("input", "19_test2.txt"));

		Set<String> molecules = new HashSet<>();
		molecules.add("e");

		for (int i = 1; i < 100; ++i) {
			molecules = step(molecules);

			System.out.println("Step " + i + ", molecules: " + molecules.size());
			if (molecules.contains(medicine)) {
				System.out.println("Found after " + i + " steps!");
				break;
			}
		}

	}

	private static Set<String> step(Set<String> molecules) {

		Set<String> newMolecules = new HashSet<>();
		for (String molecule : molecules) {
			for (Replacement r : replacements) {
				newMolecules.addAll(doReplacements(molecule, r));
			}
		}
		return newMolecules;
	}

	private static Set<String> doReplacements(String molecule, Replacement r) {

		Set<String> newMolecules = new HashSet<>();
		int pos = 0;
		while ((pos = molecule.indexOf(r.from, pos)) != -1) {
			String newMolecule = molecule.substring(0, pos) + r.to + molecule.substring(pos + r.from.length());
			if (all.add(newMolecule)) {
				newMolecules.add(newMolecule);
			}
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
