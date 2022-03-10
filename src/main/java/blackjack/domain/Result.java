package blackjack.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {

	public Map<Player, ResultType> getResult(List<Player> players, Dealer dealer) {
		final Map<Player, ResultType> gameResult = new HashMap<>();
		players.stream() // filter -> ifBurst()
			.forEach(player -> gameResult.put(player, ResultType.generateResultType(player, dealer)));
		return gameResult;
	}
}
