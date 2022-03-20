package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.BiPredicate;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum Result {
	BLACKJACK(1.5, (dealer, player) -> new ResultCalculator(dealer, player).isBlackjack()),
	LOSE(-1.0, (dealer, player) -> new ResultCalculator(dealer, player).isLose()),
	WIN(1.0, (dealer, player) -> new ResultCalculator(dealer, player).isWin()),
	DRAW(0.0, (dealer, player) -> new ResultCalculator(dealer, player).isDraw());

	private static final String ERROR_MESSAGE_NO_RULE = "[ERROR] 결과를 산출할 수 없는 경우입니다.";

	private final double multiple;
	private final BiPredicate<Dealer, Player> condition;

	Result(double multiple, BiPredicate<Dealer, Player> condition) {
		this.multiple = multiple;
		this.condition = condition;
	}

	static Result of(Dealer dealer, Player player) {
		return Arrays.stream(Result.values())
			.filter(result -> result.condition.test(dealer, player))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE_NO_RULE));
	}

	int getEarning(int amount) {
		return (int)(this.multiple * amount);
	}
}
