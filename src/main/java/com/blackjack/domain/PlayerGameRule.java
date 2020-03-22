package com.blackjack.domain;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

import com.blackjack.domain.user.Dealer;
import com.blackjack.domain.user.Player;

public enum PlayerGameRule {
	ONLY_PLAYER_BLACKJACK((dealerScore, playerScore) -> dealerScore.isNotBlackjack() && playerScore.isBlackjack(),
			ResultType.BLACKJACK_WIN),
	ONLY_DEALER_BLACKJACK((dealerScore, playerScore) -> dealerScore.isBlackjack() && playerScore.isNotBlackjack(),
			ResultType.LOSE),
	PLAYER_BUST((dealerScore, playerScore) -> playerScore.isBust(),
			ResultType.LOSE),
	ONLY_DEALER_BUST((dealerScore, playerScore) -> dealerScore.isBust() && playerScore.isNotBust(),
			ResultType.WIN),
	PLAYER_SCORE_BIGGER_THAN_DEALER((dealerScore, playerScore) -> playerScore.compareTo(dealerScore) > 0,
			ResultType.WIN),
	PLAYER_SCORE_EQUAL_TO_DEALER((dealerScore, playerScore) -> playerScore.compareTo(dealerScore) == 0,
			ResultType.DRAW),
	PLAYER_SCORE_SMALLER_THAN_DEALER((dealerScore, playerScore) -> playerScore.compareTo(dealerScore) < 0,
			ResultType.LOSE);

	private BiPredicate<Score, Score> match;
	private ResultType result;

	PlayerGameRule(BiPredicate<Score, Score> match, ResultType result) {
		this.match = match;
		this.result = result;
	}

	public static ResultType compareTo(Dealer dealer, Player player) {
		Score dealerScore = dealer.calculateScore();
		Score playerScore = player.calculateScore();
		return Stream.of(values())
				.filter(rule -> rule.isMatch(dealerScore, playerScore))
				.map(rule -> rule.result)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("결과를 판별할 수 없습니다."));
	}

	private boolean isMatch(Score dealerScore, Score playerScore) {
		return match.test(dealerScore, playerScore);
	}
}
