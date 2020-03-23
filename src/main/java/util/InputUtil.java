package util;

import java.util.Arrays;
import java.util.List;

public class InputUtil {
	private static final String DELIMITER = ",";

	public static List<String> splitNames(String combinedName) {
		return Arrays.asList(combinedName.split(DELIMITER));
	}
}
