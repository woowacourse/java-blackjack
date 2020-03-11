package com.blackjack.domain.user;

import static java.util.stream.Collectors.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.blackjack.util.NameParser;

public class PlayerFactory {
	private static final int MIN_PARTICIPANT = 1;
	private static final int MAX_PARTICIPANT = 7;

	public static List<Player> createPlayers(String input) {
		List<Name> names = NameParser.parseName(input);
		validateDuplication(names);
		validateBounds(names);
		return names.stream()
			.map(Player::new)
			.collect(toList());
	}

	private static void validateDuplication(List<Name> names) {
		Set<Name> distinctNames = new HashSet<>(names);
		if (distinctNames.size() < names.size()) {
			throw new IllegalArgumentException("중복된 이름이 존재합니다.");
		}
	}

	private static void validateBounds(List<Name> names) {
		if (names.size() < MIN_PARTICIPANT || names.size() > MAX_PARTICIPANT) {
			throw new IllegalArgumentException("플레이어의 수가 올바르지 않습니다.");
		}
	}
}
