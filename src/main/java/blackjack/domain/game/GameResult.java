package blackjack.domain.game;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.participant.Player;

public class GameResult {

	private final Map<Player, MatchResult> gameResult;

	public GameResult(Map<Player, MatchResult> gameResult) {
		this.gameResult = new LinkedHashMap<>(gameResult);
	}

	public double calculateDealerRevenue() {
		return -gameResult.keySet().stream()
			.mapToDouble(this::calculatePlayerRevenue)
			.sum();
	}

	public double calculatePlayerRevenue(Player player) {
		MatchResult matchResult = getMatchResult(player);
		return matchResult.calculateRevenue(player);
	}

	private MatchResult getMatchResult(Player player) {
		return gameResult.get(player);
	}

	public Map<Player, MatchResult> getGameResult() {
		return Collections.unmodifiableMap(gameResult);
	}
}
