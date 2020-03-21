package blackjack.domain.result;

import static java.util.stream.Collectors.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import blackjack.domain.blackjack.BlackjackTable;
import blackjack.domain.exceptions.InvalidReportException;
import blackjack.domain.user.Player;

public class Report {
	private static final int BETTING_PROFIT_MULTIPLIER_FOR_DEALER = -1;

	private final PlayersBettingMoney playersResultBettingMoney;

	private Report(PlayersBettingMoney playersResultBettingMoney) {
		this.playersResultBettingMoney = playersResultBettingMoney;
	}

	public static Report from(BlackjackTable blackjackTable, PlayersBettingMoney playersBettingMoney) {
		validate(blackjackTable, playersBettingMoney);
		return new Report(generatePlayersResult(blackjackTable, playersBettingMoney));
	}

	private static void validate(BlackjackTable blackjackTable, PlayersBettingMoney playersBettingMoney) {
		if (Objects.isNull(blackjackTable) || Objects.isNull(playersBettingMoney)) {
			throw new InvalidReportException(InvalidReportException.EMPTY);
		}
	}

	private static PlayersBettingMoney generatePlayersResult(BlackjackTable blackjackTable,
		PlayersBettingMoney playersBettingMoney) {
		ResultScore dealerResultScore = blackjackTable.getDealerResultScore();

		return blackjackTable.getPlayers().stream()
			.collect(collectingAndThen(
				toMap(
					Function.identity(),
					player -> ResultType.from(player.calculateResultScore(), dealerResultScore),
					(x, y) -> x,
					LinkedHashMap::new),
				playersBettingMoney::calculateResultBy));
	}

	public int calculateDealerProfit() {
		int playersTotalProfit = playersResultBettingMoney.getPlayersBettingMoneyToInt()
			.values()
			.stream()
			.reduce(0, Integer::sum);

		return playersTotalProfit * BETTING_PROFIT_MULTIPLIER_FOR_DEALER;
	}

	public Map<Player, Integer> calculatePlayersProfit() {
		return playersResultBettingMoney.getPlayersBettingMoneyToInt();
	}
}
