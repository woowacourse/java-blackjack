package com.blackjack.domain.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayerFactory {
	private static final int MIN_PARTICIPANT = 1;
	private static final int MAX_PARTICIPANT = 7;

	private PlayerFactory() {
	}

	public static List<Player> createPlayers(List<String> names, List<Integer> bettingMonies) {
		validateSize(names, bettingMonies);
		validateDuplication(names);
		validateBounds(names);
		return IntStream.range(0, names.size())
				.mapToObj(index -> new Player(names.get(index), bettingMonies.get(index)))
				.collect(Collectors.toList());
	}

	private static void validateSize(List<String> names, List<Integer> bettingMonies) {
		if (names.size() != bettingMonies.size()) {
			throw new IllegalArgumentException("참여자의 수와 배팅액의 수가 일치하지 않습니다.");
		}
	}

	private static void validateDuplication(List<String> names) {
		Set<String> distinctNames = new HashSet<>(names);
		if (distinctNames.size() < names.size()) {
			throw new IllegalArgumentException("중복된 이름이 존재합니다.");
		}
	}

	private static void validateBounds(List<String> names) {
		if (names.size() < MIN_PARTICIPANT || names.size() > MAX_PARTICIPANT) {
			throw new IllegalArgumentException("플레이어의 수가 올바르지 않습니다.");
		}
	}
}
