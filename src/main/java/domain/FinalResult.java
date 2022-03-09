package domain;

import java.util.Map;

public class FinalResult {

	private final Map<String, Versus> playerResults;

	public FinalResult(Dealer dealer, Players players) {
		this.playerResults = compare(dealer, players);
	}

	private Map<String, Versus> compare(Dealer dealer, Players players) {
		return players.compare(dealer);
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
