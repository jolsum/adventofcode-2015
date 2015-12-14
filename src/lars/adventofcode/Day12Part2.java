package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Day12Part2 {

	public static void main(String[] args) throws IOException, ParseException {

		String input = new String(Files.readAllBytes(Paths.get("input", "12.txt")));

		// System.out.println(sum("[1,2,3]"));
		// System.out.println(sum("[1,{\"c\":\"red\",\"b\":2},3]"));
		// System.out.println(sum("{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}"));
		// System.out.println(sum("[1,\"red\",5]"));
		System.out.println(sum(input));
	}

	private static int sum(String input) throws ParseException {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(input);
		return handleObject(obj);
	}

	private static int handleObject(final Object obj) {
		int sum = 0;
		// System.out.println(obj.getClass().getName());
		if (obj instanceof Number) {
			return ((Number) obj).intValue();
		}

		if (obj instanceof JSONObject) {
			JSONObject o = (JSONObject) obj;
			for (Object value : o.values()) {
				if (value.equals("red")) {
					return 0;
				}
				sum += handleObject(value);
			}
		}

		if (obj instanceof JSONArray) {
			JSONArray array = (JSONArray) obj;
			for (Object o : array) {
				sum += handleObject(o);
			}
		}

		return sum;
	}
}
