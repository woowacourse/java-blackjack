package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum Result {
	BLACKJACK("승", (dealer, player)
		-> !(dealer.isBlackjack()) && player.isBlackjack()),
	LOSE("패", (dealer, player)
		-> dealer.isBlackjack() && !(player.isBlackjack()) || player.isBust() || (dealer.getScore() > player.getScore())),
	WIN("승", (dealer, player)
		-> (dealer.isBust() && !(player.isBust())) || (dealer.getScore() < player.getScore())),
	DRAW("무", (dealer, player)
		-> (dealer.isBlackjack() && player.isBlackjack()) || dealer.getScore() == player.getScore());

	private final String value;
	private final BiPredicate<Dealer, Player> condition;

	Result(String value, BiPredicate<Dealer, Player> condition) {
		this.value = value;
		this.condition = condition;
	}

	public static Result of(Dealer dealer, Player player) {
		return Arrays.stream(Result.values())
			.filter(result -> result.condition.test(dealer, player))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("[ERROR] 결과를 산출할 수 없는 경우입니다."));
	}

	public String getValue() {
		return value;
	}
}
