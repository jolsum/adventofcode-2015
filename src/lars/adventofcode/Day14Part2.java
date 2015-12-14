package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public class Day14Part2 {

	private static final int TOTAL_TIME = 2503;
	private static final Collection<Raindeer> raindeer = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		try (Stream<String> lines = Files.lines(Paths.get("input", "14.txt"))) {
			lines.forEach(l -> handleLine(l));
		}

		int maxPoints = 0;
		for (int i = 1; i <= TOTAL_TIME; i++) {
			raindeer.forEach(r -> r.tick());
			final int maxDistance = raindeer.stream().mapToInt(r -> r.distance).max().getAsInt();
			raindeer.forEach(r -> r.award(maxDistance));

			maxPoints = raindeer.stream().mapToInt(r -> r.points).max().getAsInt();

		}
		System.out.println(maxPoints);
	}

	private static void handleLine(final String line) {
		String[] parts = line.substring(0, line.length() - 1).split(" ");

		Raindeer r = new Raindeer();
		r.name = parts[0];
		r.speed = Integer.parseInt(parts[3]);
		r.runTime = Integer.parseInt(parts[6]);
		r.restTime = Integer.parseInt(parts[13]);

		raindeer.add(r);
	}

	private static final class Raindeer {

		enum State {
			RUN, REST
		}

		String name;
		int speed;
		int runTime;
		int restTime;

		State state = State.RUN;
		int stateTime = 0;
		int distance = 0;
		int points = 0;

		void tick() {
			if (state == State.RUN) {
				distance += speed;
			}
			stateTime++;
			if (state == State.RUN && stateTime >= runTime) {
				state = State.REST;
				stateTime = 0;
			} else if (state == State.REST && stateTime >= restTime) {
				state = State.RUN;
				stateTime = 0;
			}
		}

		void award(int awardDistance) {
			if (distance == awardDistance) {
				points++;
			}
		}
	}

}
