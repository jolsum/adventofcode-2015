package lars.adventofcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day19 {

	private static final List<Replacement> replacements = new ArrayList<>();

	private static final Set<String> newMolecules = new HashSet<>();

	public static void main(String[] args) throws IOException {

		String start = read(new File("input", "19.txt"));

		findMolecules(start);

		System.out.println(newMolecules.size());
	}

	private static void findMolecules(String start) {

		for (Replacement r : replacements) {
			doReplacements(start, r);
		}

	}

	private static void doReplacements(String start, Replacement r) {

		int pos = 0;
		while ((pos = start.indexOf(r.from, pos)) != -1) {
			String newMolecule = start.substring(0, pos) + r.to + start.substring(pos + r.from.length());
			newMolecules.add(newMolecule);
			pos++;
		}

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
