package blackjack.domain.result;

import static java.util.stream.Collectors.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import blackjack.domain.exceptions.InvalidReportException;
import blackjack.domain.user.Player;

public class Report {
	private static final int BETTING_PROFIT_MULTIPLIER_FOR_DEALER = -1;

	private Map<Player, Integer> playersProfit;

	public Report(Map<Player, Integer> playersProfit) {
		validate(playersProfit);
		this.playersProfit = playersProfit;
	}

	private void validate(Map<Player, Integer> playersBettingMoney) {
		if (Objects.isNull(playersBettingMoney) || playersBettingMoney.isEmpty()) {
			throw new InvalidReportException(InvalidReportException.NULL);
		}
	}

	public static Report calculateResultBy(Map<Player, ResultType> playersResultType,
		Map<Player, BettingMoney> playersBettingMoney) {
		return playersResultType.entrySet().stream()
			.collect(collectingAndThen(
				toMap(
					Map.Entry::getKey,
					entry -> calculateBettingProfitBy(playersBettingMoney.get(entry.getKey()), entry.getValue()),
					(x, y) -> x,
					LinkedHashMap::new),
				Report::new));
	}

	private static Integer calculateBettingProfitBy(BettingMoney playerBettingMoney, ResultType resultType) {
		return resultType.calculateProfitFrom(playerBettingMoney);
	}

	public int getDealerBettingProfit() {
		return BETTING_PROFIT_MULTIPLIER_FOR_DEALER * playersProfit.values()
			.stream()
			.reduce(0, Integer::sum);
	}

	public Map<Player, Integer> getPlayersProfit() {
		return playersProfit;
	}
}
