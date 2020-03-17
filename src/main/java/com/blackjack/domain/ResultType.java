package com.blackjack.domain;

import java.util.function.Predicate;
import java.util.stream.Stream;

public enum ResultType {
	WIN("승", (compareResult) -> compareResult > 0),
	DRAW("무", (compareResult) -> compareResult == 0),
	LOSE("패", (compareResult) -> compareResult < 0);

	Predicate<Integer> match;
	private String alias;

	ResultType(String alias, Predicate<Integer> match) {
		this.alias = alias;
		this.match = match;
	}

	public static ResultType of(int compareResult) {
		return Stream.of(values())
				.filter(resultType -> resultType.isMatch(compareResult))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("일치하는 결과가 없습니다."));
	}

	private boolean isMatch(int compareResult) {
		return match.test(compareResult);
	}

	@Override
	public String toString() {
		return alias;
	}
}
