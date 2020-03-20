package com.blackjack.domain;

import static java.util.Objects.*;

import java.util.Collections;
import java.util.Map;

import com.blackjack.domain.user.User;

public class PlayerRecords {
	private final Map<User, Integer> records;

	public PlayerRecords(Map<User, Integer> records) {
		requireNonNull(records);
		this.records = Collections.unmodifiableMap(records);
	}

	public int calculateDealerResult() {
		return records.values()
				.stream()
				.mapToInt(profit -> -profit)
				.sum();
	}

	public Map<User, Integer> getRecords() {
		return records;
	}
}
