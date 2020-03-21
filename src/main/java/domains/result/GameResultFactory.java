package domains.result;

import java.util.HashMap;
import java.util.Map;

import domains.user.Dealer;
import domains.user.Player;
import domains.user.Players;

public class GameResultFactory {
	public static Map<Player, ResultType> create(Players players, Dealer dealer) {
		Map<Player, ResultType> playerResult = new HashMap<>();

		for (Player player : players) {
			ResultType resultType = player.checkResultType(dealer);
			playerResult.put(player, resultType);
		}

		return playerResult;
	}
}
