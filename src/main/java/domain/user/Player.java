package domain.user;

import domain.user.strategy.draw.PlayerDrawStrategy;

import java.util.Objects;

public class Player extends User {
	private final String name;

	public Player(String name) {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("이름이 빈 문자열입니다.");
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
		return Objects.equals(name, player.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
