package domain.user;

import domain.user.strategy.draw.PlayerDrawStrategy;

import java.util.Objects;

public class Player extends User {
	public static final String INPUT_EMPTY_NAME = "이름이 빈 문자열입니다.";

	private final String name;

	public Player(String name) {
		if (name.isEmpty()) {
			throw new IllegalArgumentException(INPUT_EMPTY_NAME);
		}
		this.name = name;
		super.drawStrategy = new PlayerDrawStrategy();
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Player player = (Player) o;
		return Objects.equals(name, player.name) && Objects.equals(cards, player.cards);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
