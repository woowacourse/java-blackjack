package domain.gamer;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum MatchResult {
	BLACKJACK("블랙잭", (playerScore, dealerScore) -> playerScore == 21),
	BUST("버스트", (playerScore, dealerScore) -> playerScore > 21),
	WIN("승", (playerScore, dealerScore) -> playerScore > dealerScore || dealerScore > 21),
	DRAW("무", Integer::equals),
	LOSE("패", (playerScore, dealerScore) -> playerScore < dealerScore || playerScore > 21);

	private final String initial;
	private final BiPredicate<Integer, Integer> matchResultPredicate;

	MatchResult(String initial, BiPredicate<Integer, Integer> matchResultPredicate) {
		this.initial = initial;
		this.matchResultPredicate = matchResultPredicate;
	}

	public static MatchResult of(int playerScore, int dealerScore, int cardSize) {
		return Arrays.stream(MatchResult.values())
			.filter(x -> x.matchResultPredicate.test(playerScore, dealerScore))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("잘못된 값입니다."));
	}

	public String getInitial() {
		return initial;
	}
}