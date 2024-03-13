package blackjack.domain.game;

import blackjack.domain.gamer.Money;
import blackjack.domain.gamer.Player;

public class BlackjackRevenueCalculator {

	private final PlayerResultHandler handler;

	public BlackjackRevenueCalculator(PlayerResultHandler handler) {
		this.handler = handler;
	}

	public Money calculatePlayerRevenue(Player player, Money betAmount) {
		GameResult gameResult = handler.notifyResultToPlayer(player);

		return getRevenue(gameResult, betAmount);
	}

	private Money getRevenue(GameResult gameResult, Money betAmount) {
		if (gameResult == GameResult.BLACKJACK_WIN) {
			return betAmount.multiplyByRatio(1.5);
		}
		if (gameResult == GameResult.WIN) {
			return betAmount;
		}
		if (gameResult == GameResult.LOSE) {
			return betAmount.multiplyByRatio(-1);
		}
		return new Money(0);
	}
}
