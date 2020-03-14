package com.blackjack.domain;

import static java.util.stream.Collectors.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.blackjack.domain.user.Dealer;
import com.blackjack.domain.user.User;

public class GameRule {
	private final Dealer dealer;
	private final List<User> players;

	public GameRule(Dealer dealer, List<User> players) {
		Objects.requireNonNull(dealer);
		Objects.requireNonNull(players);
		this.dealer = dealer;
		this.players = players;
	}

	public PlayerRecords calculateResult() {
		return players.stream()
				.collect(Collectors.collectingAndThen(toMap(
						player -> player,
						player -> player.compareScoreTo(dealer),
						(r1, r2) -> r1,
						LinkedHashMap::new
				), PlayerRecords::new));
	}
}
