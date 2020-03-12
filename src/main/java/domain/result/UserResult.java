package domain.result;

import java.util.Objects;

import domain.user.User;

public class UserResult {
	private final User user;
	private final MatchResult matchResult;

	public UserResult(User user, MatchResult matchResult) {
		this.user = Objects.requireNonNull(user);
		this.matchResult = Objects.requireNonNull(matchResult);
	}

	public String getName() {
		return user.getName();
	}

	public String getMatchResult() {
		return matchResult.getMatchResult();
	}
}
