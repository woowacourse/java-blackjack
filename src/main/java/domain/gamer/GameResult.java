package domain.gamer;

import static java.util.stream.Collectors.*;

import java.util.Map;

public class GameResult {
	private Map<Player, MatchResult> gameResult;

	public GameResult(Map<Player, MatchResult> gameResult) {
		this.gameResult = gameResult;
	}

	public Map<Player, Money> getTotalEarning() {
		return gameResult.keySet()
			.stream()
			.collect(toMap(player -> player, player -> gameResult.get(player).
				getEarnCalculator().apply(player.getMoney())));
	}
}
