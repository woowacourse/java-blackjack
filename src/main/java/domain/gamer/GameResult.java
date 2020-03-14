package domain.gamer;

import java.util.Map;

public class GameResult {
	private Map<Player, MatchResult> gameResult;

	public GameResult(Map<Player, MatchResult> gameResult) {
		this.gameResult = gameResult;
	}

	public Map<Player, MatchResult> getGameResult() {
		return gameResult;
	}
}
