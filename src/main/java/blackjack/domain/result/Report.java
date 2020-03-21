package blackjack.domain.result;

import static java.util.stream.Collectors.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import blackjack.domain.exceptions.InvalidReportException;
import blackjack.domain.user.Player;

public class Report {
	private static final int BETTING_PROFIT_MULTIPLIER_FOR_DEALER = -1;

	private Map<Player, BettingMoney> playersBettingMoney;

	public Report(Map<Player, BettingMoney> playersBettingMoney) {
		validate(playersBettingMoney);
		this.playersBettingMoney = playersBettingMoney;
	}

	private void validate(Map<Player, BettingMoney> playersBettingMoney) {
		if (Objects.isNull(playersBettingMoney) || playersBettingMoney.isEmpty()) {
			throw new InvalidReportException(InvalidReportException.NULL);
		}
	}

	public Report calculateResultBy(Map<Player, ResultType> playersResultType) {
		return playersResultType.entrySet().stream()
			.collect(collectingAndThen(
				toMap(
					Map.Entry::getKey,
					entry -> calculateBettingProfitBy(entry.getKey(), entry.getValue()),
					(x, y) -> x,
					LinkedHashMap::new),
				Report::new));
	}

	private BettingMoney calculateBettingProfitBy(Player player, ResultType resultType) {
		return resultType.calculateProfitFrom(playersBettingMoney.get(player));
	}

	public int getDealerBettingProfit() {
		return BETTING_PROFIT_MULTIPLIER_FOR_DEALER * getPlayersBettingProfit().values()
			.stream()
			.reduce(0, Integer::sum);
	}

	public Map<Player, Integer> getPlayersBettingProfit() {
		return playersBettingMoney.keySet().stream()
			.collect(toMap(
				Function.identity(),
				player -> playersBettingMoney.get(player).getBettingMoney(),
				(x, y) -> x,
				LinkedHashMap::new));
	}
}
