package domains.result;

import static java.util.stream.Collectors.*;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import domains.user.Dealer;
import domains.user.Player;
import domains.user.Players;

public class GameResult {
	private Map<Player, ResultType> gameResult;

	public GameResult(Players players, Dealer dealer) {
		this.gameResult = new HashMap<>();
		create(players, dealer);
	}

	private void create(Players players, Dealer dealer) {
		for (Player player : players) {
			if (checkBurstPlayer(player)) {
				continue;
			}
			gameResult.put(player, ResultType.checkWinOrLose(player, dealer));
		}
	}

	private boolean checkBurstPlayer(Player player) {
		if (player.isBurst()) {
			gameResult.put(player, ResultType.LOSE);
			return true;
		}
		return false;
	}

	public ResultType getWinOrLose(Player player) {
		return gameResult.get(player);
	}

	public Map<ResultType, Long> calculateDealerResult() {
		return gameResult.values().stream()
			.map(ResultType::oppose)
			.collect(
				Collectors.collectingAndThen(Collectors.groupingBy(Function.identity(), counting()), EnumMap::new));
	}

	public Map<Player, ResultType> getGameResult() {
		return gameResult;
	}
}
