package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result {
	private final Map<Player, ResultType> gameResult = new LinkedHashMap<>();

	public Result(Players players) {
		for (Player player : players.getPlayers()) {
			gameResult.put(player, ResultType.DRAW);
		}
	}

	public Map<Player, ResultType> getResult(Players players, Dealer dealer) {
		players.getPlayers().stream().filter(dealer::isWin).forEach(player -> gameResult.put(player, ResultType.LOSE));
		players.getPlayers().stream().filter(dealer::isDraw).forEach(player -> gameResult.put(player, ResultType.DRAW));
		players.getPlayers().stream().filter(dealer::isLose).forEach(player -> gameResult.put(player, ResultType.WIN));
		return gameResult;
	}

	public int calculateCount(ResultType resultType) {
		return (int) this.gameResult.values().stream()
			.filter(each -> each == resultType)
			.count();
	}

	private void insertInGameResult(List<Player> players, ResultType resultType) {
		for (Player player : players) {
			this.gameResult.put(player, resultType);
		}
	}
}
