package com.blackjack.domain;

import static java.util.Objects.*;
import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.blackjack.domain.user.Dealer;
import com.blackjack.domain.user.Player;
import com.blackjack.domain.user.User;

public class PlayerRecords {
	private final Map<User, Integer> records;

	public PlayerRecords(Map<User, Integer> records) {
		this.records = Collections.unmodifiableMap(records);
	}

	public static PlayerRecords createPlayerRecords(Dealer dealer, List<Player> players) {
		requireNonNull(dealer);
		requireNonNull(players);
		return players.stream()
				.collect(Collectors.collectingAndThen(toMap(
						player -> player,
						player -> calculateProfit(player, dealer),
						(r1, r2) -> r1,
						LinkedHashMap::new
				), PlayerRecords::new));
	}

	private static Integer calculateProfit(Player player, Dealer dealer) {
		ResultType result = compareScore(player, dealer);
		return player.calculateProfit(result);
	}

	private static ResultType compareScore(Player player, Dealer dealer) {
		Score playerScore = player.calculateScore();
		Score dealerScore = dealer.calculateScore();
		if (playerScore.isBust()) {
			return ResultType.LOSE;
		}
		return ResultType.of(playerScore, dealerScore);
	}

	public int calculateDealerResult() {
		return records.values()
				.stream()
				.mapToInt(profit -> profit * -1)
				.sum();
	}

	public Map<User, Integer> getRecords() {
		return records;
	}
}
