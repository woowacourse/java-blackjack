package blackjack.domain.game;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

public class GameResult {

	private final Map<Player, MatchResult> gameResult;

	public GameResult(Players players, Dealer dealer) {
		gameResult = new LinkedHashMap<>(players.match(dealer));
	}

	public double calculateDealerRevenue() {
		return -gameResult.keySet().stream()
			.mapToDouble(this::calculatePlayerRevenue)
			.sum();
	}

	public double calculatePlayerRevenue(Player player) {
		MatchResult matchResult = findMatchResult(player);
		return matchResult.calculateRevenue(player);
	}

	private MatchResult findMatchResult(Player player) {
		return gameResult.get(player);
	}

	public Map<Player, MatchResult> getGameResult() {
		return Collections.unmodifiableMap(gameResult);
	}
}
