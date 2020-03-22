package com.blackjack.domain;

import java.util.Collections;
import java.util.Map;

import com.blackjack.domain.user.User;

public class PlayerProfits {
	private final Map<User, Integer> records;

	public PlayerProfits(Map<User, Integer> records) {
		this.records = Collections.unmodifiableMap(records);
	}

	public int calculateDealerProfit() {
		return calculateAllUserProfits() * -1;
	}

	private int calculateAllUserProfits() {
		return records.values()
				.stream()
				.mapToInt(Integer::intValue)
				.sum();
	}

	public Map<User, Integer> getRecords() {
		return records;
	}
}
