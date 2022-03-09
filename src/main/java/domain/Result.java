package domain;

import java.util.Map;

public class Result {

	private final Map<String, Versus> playerResults;

	// //public Result(Dealer dealer, Players players) {
	// 	this.playerResults = players.compare(dealer);
	// }

	public Result(Map<String, Versus> playerResults) {
		this.playerResults = playerResults;
	}

	public Versus getVersus(String name) {
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
