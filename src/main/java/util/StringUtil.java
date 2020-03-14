package util;

import java.util.Arrays;
import java.util.List;

public class StringUtil {
	private static final String COMMA = ",";

	public static List<String> parseByComma(String input) {
		return Arrays.asList(input.split(COMMA));
	}
}