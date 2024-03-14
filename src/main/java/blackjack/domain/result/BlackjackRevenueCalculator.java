package blackjack.domain.result;

import java.util.List;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Money;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

public class BlackjackRevenueCalculator {

	private static final double BLACKJACK_REVENUE_RATIO = 1.5D;
	private static final double LOSE_REVENUE_RATIO = -1.0D;

	private final PlayerResultHandler playerResultHandler;

	public BlackjackRevenueCalculator(PlayerResultHandler playerResultHandler) {
		this.playerResultHandler = playerResultHandler;
	}

	public static BlackjackRevenueCalculator fromDealer(Dealer dealer) {
		PlayerResultHandler resultHandler = new PlayerResultHandler(dealer);

		return new BlackjackRevenueCalculator(resultHandler);
	}

	public Money calculateDealerRevenue(Players players) {
		List<Player> losePlayers = playerResultHandler.getLosePlayers(players.getPlayers());

		if (losePlayers.isEmpty()) {
			return new Money(0);
		}

		return losePlayers.stream()
			.map(players::getBetAmount)
			.reduce(new Money(0), Money::add);
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
