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
	private final Map<Player, BettingMoney> playersResult;

	private Report(Map<Player, BettingMoney> playersResult) {
		this.playersResult = playersResult;
	}

	public static Report from(BlackjackTable blackjackTable, Map<Player, BettingMoney> playersBettingMoney) {
		validate(blackjackTable, playersBettingMoney);
		return new Report(generatePlayersResult(blackjackTable, playersBettingMoney));
	}

	private static void validate(BlackjackTable blackjackTable, Map<Player, BettingMoney> playersBettingMoney) {
		if (Objects.isNull(blackjackTable) || Objects.isNull(playersBettingMoney) || playersBettingMoney.isEmpty()) {
			throw new InvalidReportException(InvalidReportException.EMPTY);
		}
	}

	private static Map<Player, BettingMoney> generatePlayersResult(BlackjackTable blackjackTable,
		Map<Player, BettingMoney> playersBettingMoney) {
		ResultScore dealerResultScore = blackjackTable.getDealerResultScore();

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
		int playersTotalProfit = playersResult.values().stream()
			.map(BettingMoney::getBettingMoney)
			.reduce(0, Integer::sum);
		return playersTotalProfit * BETTING_PROFIT_MULTIPLIER_FOR_DEALER;
	}

	public Map<Player, Integer> calculatePlayersProfit() {
		return playersResult.keySet().stream()
			.collect(collectingAndThen(
				toMap(Function.identity(),
					player -> playersResult.get(player).getBettingMoney(),
					(x, y) -> x,
					LinkedHashMap::new),
				Collections::unmodifiableMap));
	}
}
