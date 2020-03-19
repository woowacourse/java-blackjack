package com.blackjack.util;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;

public class StringUtil {
	private static final String DELIMITER = ",";

	private StringUtil() {
	}

	public static List<String> splitByDelimiter(String input) {
		return Stream.of(input.split(DELIMITER))
				.map(String::trim)
				.collect(toList());
	}
}
