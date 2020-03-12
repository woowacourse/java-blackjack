package domain.result;

import java.util.Arrays;
import java.util.function.BiPredicate;

import domain.user.User;

public enum MatchResult {
	WIN("승", (player, dealer) -> (dealer.isBust() && !player.isBust()) || (player.calculateScore() > dealer.calculateScore())),
	DRAW("무", (player, dealer) -> dealer.isBlackjack() && player.isBlackjack()),
	LOSE("패", (player, dealer) -> (player.isBust()) || (player.calculateScore() <= dealer.calculateScore()));

	private final String matchResult;
	private final BiPredicate<User, User> resultCondition;

	MatchResult(String matchResult, BiPredicate<User, User> resultCondition) {
		this.matchResult = matchResult;
		this.resultCondition = resultCondition;
	}

	public static MatchResult findMatchResult(User player, User dealer) {
		return Arrays.stream(values())
			.filter(result -> result.resultCondition.test(player, dealer))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
	}
}
