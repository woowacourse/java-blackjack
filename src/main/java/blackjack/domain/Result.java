package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum Result {
	BLACKJACK((dealer, player)
		-> !(dealer.isBlackjack()) && player.isBlackjack(),
		1.5),
	LOSE((dealer, player)
		-> dealer.isBlackjack() && !(player.isBlackjack()) || player.isBust() || (dealer.getScore() > player.getScore()),
		-1.0),
	WIN((dealer, player)
		-> (dealer.isBust() && !(player.isBust())) || (dealer.getScore() < player.getScore()),
		1.0),
	DRAW((dealer, player)
		-> (dealer.isBlackjack() && player.isBlackjack()) || dealer.getScore() == player.getScore(),
		0.0);

	private final BiPredicate<Dealer, Player> condition;
	private final double multiple;

	Result(BiPredicate<Dealer, Player> condition, double multiple) {
		this.condition = condition;
		this.multiple = multiple;
	}

	public static Result of(Dealer dealer, Player player) {
		return Arrays.stream(Result.values())
			.filter(result -> result.condition.test(dealer, player))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("[ERROR] 결과를 산출할 수 없는 경우입니다."));
	}

	public int getEarning(int amount) {
		return (int)(this.multiple * amount);
	}
}
