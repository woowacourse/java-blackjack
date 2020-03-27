package domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

import domain.participant.Dealer;
import domain.participant.Player;

public enum Result {
	BLACKJACKWIN("블랙잭", Player::isWinByBlackJack, 1.5),
	WIN("승", Player::isWin, 1),
	LOSE("패", Player::isLose, -1),
	DRAW("무", Player::isDraw, 0);

	private final String name;
	private final BiPredicate<Player, Dealer> condition;
	private final double profitRate;

	Result(String name,
		BiPredicate<Player, Dealer> condition, double profitRate) {
		this.name = name;
		this.condition = condition;
		this.profitRate = profitRate;
	}

	public static Result of(Player player, Dealer dealer) {
		return Arrays.stream(Result.values())
			.filter(result -> result.condition.test(player, dealer))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("해당하는 결과값을 찾을 수 없습니다."));
	}

	public static double deduceProfitRate(Player player, Dealer dealer) {
		return Result.of(player, dealer).getProfitRate();
	}

	public String getName() {
		return name;
	}

	public double getProfitRate() {
		return profitRate;
	}
}
