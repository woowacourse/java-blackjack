package blackjack.domain.game;

import blackjack.domain.gamer.Money;
import blackjack.domain.gamer.Player;

public class BlackjackRevenueCalculator {

	private static final double BLACKJACK_REVENUE_RATIO = 1.5D;
	private static final double LOSE_REVENUE_RATIO = -1.0D;

	private final PlayerResultHandler playerResultHandler;

	public BlackjackRevenueCalculator(PlayerResultHandler playerResultHandler) {
		this.playerResultHandler = playerResultHandler;
	}

	public Money calculatePlayerRevenue(Player player, Money betAmount) {
		GameResult gameResult = playerResultHandler.getPlayerResult(player);

		return getRevenue(gameResult, betAmount);
	}

	private Money getRevenue(GameResult gameResult, Money betAmount) {
		if (gameResult == GameResult.BLACKJACK_WIN) {
			return betAmount.multiplyByRatio(BLACKJACK_REVENUE_RATIO);
		}
		if (gameResult == GameResult.WIN) {
			return betAmount;
		}
		if (gameResult == GameResult.LOSE) {
			return betAmount.multiplyByRatio(LOSE_REVENUE_RATIO);
		}
		return new Money(0);
	}
}
