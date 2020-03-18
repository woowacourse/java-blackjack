package com.blackjack.domain;

import static java.util.stream.Collectors.toMap;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.blackjack.domain.user.Dealer;
import com.blackjack.domain.user.Player;
import com.blackjack.domain.user.User;

public class GameRule {
	private final Dealer dealer;
	private final List<User> players;

	public GameRule(Dealer dealer, List<User> players) {
		validateNullOrEmpty(dealer, players);
		this.dealer = dealer;
		this.players = players;
	}

	private void validateNullOrEmpty(Dealer dealer, List<User> players) {
		if (Objects.isNull(dealer) || Objects.isNull(players) || players.isEmpty()) {
			throw new IllegalArgumentException("딜러 또는 플레이어가 존재하지 않습니다.");
		}
	}

	public PlayerRecords calculateResult() {
		return players.stream()
				.collect(Collectors.collectingAndThen(toMap(
						player -> player,
						player -> compareScore((Player)player, dealer),
						(r1, r2) -> r1,
						LinkedHashMap::new
				), PlayerRecords::new));
	}

	public ResultType compareScore(Player player, Dealer dealer) {
		Score playerScore = player.calculateHand();
		Score dealerScore = dealer.calculateHand();
		if (playerScore.isBust()) {
			return ResultType.LOSE;
		}
		return ResultType.of(playerScore, dealerScore);
	}
}
