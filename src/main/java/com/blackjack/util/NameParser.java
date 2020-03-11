package com.blackjack.util;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.stream.Stream;

import com.blackjack.domain.user.Name;

public class NameParser {
	private static final String DELIMITER = ",";

	public static List<Name> parseName(String input) {
		return Stream.of(input.split(DELIMITER))
			.map(String::trim)
			.map(Name::new)
			.collect(toList());
	}
}
