package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Result {
	private final Map<Player, ResultType> gameResult = new LinkedHashMap<>();

	public Result(Players players) {
		for (Player player : players.getPlayers()) {
			gameResult.put(player, ResultType.DRAW);
		}
	}

	public Map<Player, ResultType> getResult(Players players, Dealer dealer) {
		insertInGameResult(players.getBustPlayers(), ResultType.LOSE);
		insertInGameResult(players.getNotBustPlayers().stream().filter(dealer::isWin).collect(Collectors.toList()),
			ResultType.LOSE);
		insertInGameResult(players.getNotBustPlayers().stream().filter(dealer::isDraw).collect(Collectors.toList()),
			ResultType.DRAW);
		insertInGameResult(players.getNotBustPlayers().stream().filter(dealer::isLose).collect(Collectors.toList()),
			ResultType.WIN);
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
