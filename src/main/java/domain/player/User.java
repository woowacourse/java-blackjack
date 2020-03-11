package domain.player;

import domain.card.PlayerCards;

public class User extends Player {
	private static final String ERROR_MESSAGE_NAME_BLANK = "이름은 blank 값이 될 수 없습니다.";

	public User(String name) {
		validateName(name);
		this.name = name;
		this.playerCards = new PlayerCards();
	}

	private void validateName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException(ERROR_MESSAGE_NAME_BLANK);
		}
	}
}
