package blackjack.domain.result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

public class Result {

	public Map<Player, ResultType> getResult(final List<Player> players, final Dealer dealer) {
		final Map<Player, ResultType> gameResult = new HashMap<>();
		players.stream()
			.forEach(player -> gameResult.put(player, ResultType.getMatchedResultType(player, dealer)));
		return gameResult;
	}
}
