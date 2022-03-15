package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {
	private final Map<Player, ResultType> gameResult;

	private Result(Players players) {
		gameResult = new LinkedHashMap<>();
		players.getPlayers()
			.forEach(player -> gameResult.put(player, ResultType.DRAW));
	}

	public static Result of(Players players, Dealer dealer) {
		Result result = new Result(players);
		players.getPlayers().stream().filter(dealer::isWin)
			.forEach(player -> result.gameResult.put(player, ResultType.LOSE));
		players.getPlayers().stream().filter(dealer::isDraw)
			.forEach(player -> result.gameResult.put(player, ResultType.DRAW));
		players.getPlayers().stream().filter(dealer::isLose)
			.forEach(player -> result.gameResult.put(player, ResultType.WIN));
		return result;
	}

	public int calculateCount(ResultType resultType) {
		return (int) this.gameResult.values().stream()
			.filter(each -> each == resultType)
			.count();
	}

	public Map<Player, ResultType> getGameResult() {
		return gameResult;
	}
}
