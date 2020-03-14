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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PlayerResult that = (PlayerResult)o;
		return Objects.equals(player, that.player) &&
			matchResult == that.matchResult;
	}

	@Override
	public int hashCode() {
		return Objects.hash(player, matchResult);
	}
}
