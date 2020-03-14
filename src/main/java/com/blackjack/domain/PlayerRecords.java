package com.blackjack.domain;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.blackjack.domain.user.User;

public class PlayerRecords {
	private final Map<User, ResultType> records;

	public PlayerRecords(Map<User, ResultType> records) {
		Objects.requireNonNull(records);
		this.records = Collections.unmodifiableMap(records);
	}

	public Map<ResultType, Long> calculateDealerResult() {
		return records.values()
				.stream()
				.map(ResultType::reverseResultType)
				.collect(Collectors.groupingBy(Function.identity(), counting()));
	}

	public Map<User, ResultType> getRecords() {
		return records;
	}
}
