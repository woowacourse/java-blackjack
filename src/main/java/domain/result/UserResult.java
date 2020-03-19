package domain.result;

import java.util.Objects;

import domain.user.User;

public class UserResult {
	private final User user;
	private final Prize prize;

	public UserResult(User user, Prize prize) {
		this.user = Objects.requireNonNull(user);
		this.prize = prize;
	}

	public String getName() {
		return user.getName();
	}

	public Prize getPrize() {
		return prize;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		UserResult that = (UserResult)object;
		return Objects.equals(this.user, that.user) &&
			Objects.equals(this.prize, that.prize);
	}

	@Override
	public int hashCode() {
		return Objects.hash(user, prize);
	}
}
