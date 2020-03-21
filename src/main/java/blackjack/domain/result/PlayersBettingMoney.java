package blackjack.domain.result;

import static java.util.stream.Collectors.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import blackjack.domain.exceptions.InvalidPlayersBettingMoneyException;
import blackjack.domain.user.Player;

public class PlayersBettingMoney {
	private Map<Player, BettingMoney> playersBettingMoney;

	public PlayersBettingMoney(Map<Player, BettingMoney> playersBettingMoney) {
		validate(playersBettingMoney);
		this.playersBettingMoney = playersBettingMoney;
	}

	private void validate(Map<Player, BettingMoney> playersBettingMoney) {
		if (Objects.isNull(playersBettingMoney) || playersBettingMoney.isEmpty()) {
			throw new InvalidPlayersBettingMoneyException(InvalidPlayersBettingMoneyException.NULL);
		}
	}

	public PlayersBettingMoney calculateResultBy(Map<Player, ResultType> playersResultType) {
		return playersResultType.entrySet().stream()
			.collect(collectingAndThen(
				toMap(
					Map.Entry::getKey,
					entry -> calculateResultBettingMoneyBy(entry.getKey(), entry.getValue()),
					(x, y) -> x,
					LinkedHashMap::new),
				PlayersBettingMoney::new));
	}

	private BettingMoney calculateResultBettingMoneyBy(Player player, ResultType resultType) {
		return resultType.calculateProfitFrom(playersBettingMoney.get(player));
	}

	public BettingMoney getBettingMoneyFrom(Player player) {
		return playersBettingMoney.get(player);
	}

	Map<Player, Integer> getPlayersBettingMoneyToInt() {
		return playersBettingMoney.keySet().stream()
			.collect(toMap(
				Function.identity(),
				player -> playersBettingMoney.get(player).getBettingMoney(),
				(x, y) -> x,
				LinkedHashMap::new));
	}
}
