package com.blackjack.domain;

import static java.util.stream.Collectors.toMap;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.blackjack.domain.user.Dealer;
import com.blackjack.domain.user.Player;

public class GameRule {
	private final Dealer dealer;
	private final List<Player> players;

	public GameRule(Dealer dealer, List<Player> players) {
		validateNullOrEmpty(dealer, players);
		this.dealer = dealer;
		this.players = players;
	}

	private void validateNullOrEmpty(Dealer dealer, List<Player> players) {
		if (Objects.isNull(dealer) || Objects.isNull(players) || players.isEmpty()) {
			throw new IllegalArgumentException("딜러 또는 플레이어가 존재하지 않습니다.");
		}
	}

	public PlayerProfits calculatePlayerProfits() {
		return players.stream()
				.collect(Collectors.collectingAndThen(toMap(
						player -> player,
						player -> calculateProfit(dealer, player),
						(r1, r2) -> r1,
						LinkedHashMap::new
				), PlayerProfits::new));
	}

	private Integer calculateProfit(Dealer dealer, Player player) {
		ResultType result = PlayerGameRule.compareTo(dealer, player);
		return player.calculateProfit(result.getProfitRate());
	}
}
