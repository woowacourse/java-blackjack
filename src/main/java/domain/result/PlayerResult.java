package domain.result;

import java.util.Objects;

import domain.user.Player;

public class PlayerResult {
	private final Player player;
	private final MatchResult matchResult;

	public PlayerResult(Player player, MatchResult matchResult) {
		this.player = Objects.requireNonNull(player);
		this.matchResult = Objects.requireNonNull(matchResult);
	}

	public String getName() {
		return player.getName();
	}

	public String getMatchResult() {
		return matchResult.getMatchResult();
	}
}
