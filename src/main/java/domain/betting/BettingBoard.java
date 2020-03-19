package domain.betting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import domain.user.User;

public class BettingBoard {
	private static final String NOT_MATCH_SIZE_MESSAGE = "사용자의 수와 금액의 수가 일치하지 않습니다.";

	private final Map<User, Money> bettingBoard;

	public BettingBoard(List<User> users, List<Money> moneys) {
		Objects.requireNonNull(users);
		Objects.requireNonNull(moneys);
		validateSizeMatch(users, moneys);
		bettingBoard = new HashMap<>();
		for (int index = 0; index < users.size(); index++) {
			bettingBoard.put(users.get(index), moneys.get(index));
		}
	}

	private void validateSizeMatch(List<User> users, List<Money> moneys) {
		if (users.size() != moneys.size()) {
			throw new IllegalArgumentException(NOT_MATCH_SIZE_MESSAGE);
		}
	}

	public Money get(User user) {
		return bettingBoard.get(user);
	}

}
