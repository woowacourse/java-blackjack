package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

public class Result {
	private final Map<Player, ResultType> gameResult = new HashMap<>();

	public Map<Player, ResultType> getResult(Players players, Dealer dealer) {
		players.getPlayers().stream()
			.forEach(player -> gameResult.put(player, ResultType.generateResultType(player, dealer)));
		return gameResult;
	}

	public int calculateCount(ResultType resultType) {
		return (int) this.gameResult.values().stream()
			.filter(each -> each == resultType)
			.count();
	}
}
