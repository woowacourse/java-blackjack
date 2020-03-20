package com.blackjack.domain;

import java.util.Collections;
import java.util.Map;

import com.blackjack.domain.user.User;

public class PlayerRecords {
	private final Map<User, Integer> records;

	public PlayerRecords(Map<User, Integer> records) {
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
