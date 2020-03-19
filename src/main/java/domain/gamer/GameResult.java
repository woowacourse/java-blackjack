package domain.gamer;

import static java.util.stream.Collectors.*;

import java.util.LinkedHashMap;
import java.util.Map;

import exception.MissResultException;

public class GameResult {
	private Map<Player, MatchResult> gameResult;

	public GameResult(Map<Player, MatchResult> gameResult) {
		this.gameResult = gameResult;
	}

	public Map<Player, Money> getPlayersTotalEarning() {
		return gameResult.keySet()
			.stream()
			.collect(toMap(player -> player, player -> gameResult.get(player).
					getEarnCalculator().apply(player.getMoney()),
				(a, b) -> a,
				LinkedHashMap::new));
	}

	public Money getDealerEarning() {
		return getPlayersTotalEarning().values()
			.stream()
			.map(Money::reversion)
			.reduce(Money::sum)
			.orElseThrow(MissResultException::new);
	}
}
