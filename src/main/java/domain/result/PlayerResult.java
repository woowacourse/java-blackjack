package domain.result;

import java.util.Objects;

import domain.user.User;

public class PlayerResult {
	private final User user;
	private final MatchResult matchResult;

	public PlayerResult(User user, MatchResult matchResult) {
		this.user = Objects.requireNonNull(user);
		this.matchResult = Objects.requireNonNull(matchResult);
	}

	public String getName() {
		return user.getName();
	}

	public String getMatchResult() {
		return matchResult.getMatchResult();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		PlayerResult that = (PlayerResult)object;
		return Objects.equals(user, that.user) &&
			this.matchResult == that.matchResult;
	}

	@Override
	public int hashCode() {
		return Objects.hash(user, matchResult);
	}
}
