package view.dto;

import java.util.Objects;

import domain.result.Prize;
import domain.user.User;

public class UserPrizeDto {
	private final User user;
	private final Prize prize;

	public UserPrizeDto(User user, Prize prize) {
		this.user = Objects.requireNonNull(user);
		this.prize = Objects.requireNonNull(prize);
	}

	public String getName() {
		return user.getName();
	}

	public Prize getPrize() {
		return prize;
	}
}
