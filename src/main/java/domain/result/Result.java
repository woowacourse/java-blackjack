package domain.result;

import java.util.Map;

import domain.participant.Name;

public class Result {

	private final Map<Name, Versus> playerResults;

	public Result(Map<Name, Versus> playerResults) {
		this.playerResults = Map.copyOf(playerResults);
	}

	public Versus getVersusOfPlayer(Name name) {
		return playerResults.get(name);
	}

	public int getDealerWinCount() {
		return (int)playerResults.keySet().stream()
			.filter(key -> playerResults.get(key) == Versus.LOSE)
			.count();
	}

	public int getDealerDrawCount() {
		return (int)playerResults.keySet().stream()
			.filter(key -> playerResults.get(key) == Versus.DRAW)
			.count();
	}

	public int getDealerLoseCount() {
		return (int)playerResults.keySet().stream()
			.filter(key -> playerResults.get(key) == Versus.WIN)
			.count();
	}
}
