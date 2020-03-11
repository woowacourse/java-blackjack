package domain.player;

import domain.card.PlayerCards;

public class User extends Player {
	public User(String name) {
		validateName(name);
		this.name = name;
		this.playerCards = new PlayerCards();
	}

	private void validateName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("이름은 blank 값이 될 수 없습니다.");
		}
	}
}
