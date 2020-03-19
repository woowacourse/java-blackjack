package blackjack.domain.result;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import blackjack.domain.blackjack.BlackjackTable;
import blackjack.domain.exceptions.InvalidReportException;
import blackjack.domain.user.Player;

public class Report {
	private static final int BETTING_PROFIT_MULTIPLIER_FOR_DEALER = -1;
	private final Map<Player, BettingMoney> playersProfit;

	private Report(Map<Player, BettingMoney> playersProfit) {
		this.playersProfit = playersProfit;
	}

	public static Report from(BlackjackTable blackjackTable) {
		validate(blackjackTable);
		return new Report(generatePlayersProfit(blackjackTable));
	}

	private static void validate(BlackjackTable blackjackTable) {
		if (Objects.isNull(blackjackTable)) {
			throw new InvalidReportException(InvalidReportException.EMPTY);
		}
	}

	private static Map<Player, BettingMoney> generatePlayersProfit(BlackjackTable blackjackTable) {
		ResultScore dealerResultScore = blackjackTable.getDealerResultScore();
		Map<Player, BettingMoney> playersBettingMoney = blackjackTable.getPlayersBettingMoney();

		return blackjackTable.getPlayers().stream()
			.collect(collectingAndThen(
				toMap(Function.identity(),
					player -> ResultType.from(player.calculateResultScore(), dealerResultScore)
						.calculateProfitFrom(playersBettingMoney.get(player)),
					(x, y) -> x,
					LinkedHashMap::new),
				Collections::unmodifiableMap));
	}

	public int calculateDealerProfit() {
		int playersTotalProfit = playersProfit.values().stream()
			.map(BettingMoney::getBettingMoney)
			.reduce(0, Integer::sum);
		return playersTotalProfit * BETTING_PROFIT_MULTIPLIER_FOR_DEALER;
	}

	public Map<Player, Integer> getPlayersProfit() {
		return playersProfit.keySet().stream()
			.collect(collectingAndThen(
				toMap(Function.identity(),
					player -> playersProfit.get(player).getBettingMoney(),
					(x, y) -> x,
					LinkedHashMap::new),
				Collections::unmodifiableMap));
	}
}
