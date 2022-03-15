package domain.result;

import java.util.Map;

import domain.participant.Name;

public class Result {

	private final Map<Name, WinOrLose> playerResults;

	public Result(Map<Name, WinOrLose> playerResults) {
		this.playerResults = Map.copyOf(playerResults);
	}

	public WinOrLose getResultOfPlayer(Name name) {
		return playerResults.get(name);
	}

	public int getDealerWinCount() {
		return (int)playerResults.keySet().stream()
			.filter(key -> playerResults.get(key) == WinOrLose.LOSE)
			.count();
	}

	public int getDealerDrawCount() {
		return (int)playerResults.keySet().stream()
			.filter(key -> playerResults.get(key) == WinOrLose.DRAW)
			.count();
	}

	public int getDealerLoseCount() {
		return (int)playerResults.keySet().stream()
			.filter(key -> playerResults.get(key) == WinOrLose.WIN)
			.count();
	}
}
