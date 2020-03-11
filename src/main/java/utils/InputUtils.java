package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputUtils {
	private static final String DELIMITER = ",";

	public static List<String> splitAsDelimiter(String input) {
		return Arrays.stream(input.split(DELIMITER))
				.map(String::trim)
				.collect(Collectors.toList());
	}
}
