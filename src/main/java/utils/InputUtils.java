package utils;

import java.util.Arrays;
import java.util.List;

public class InputUtils {
	private static final String DELIMITER = ",";

	public static List<String> splitAsDelimiter(String input) {
		return Arrays.asList(input.split(DELIMITER));
	}
}
