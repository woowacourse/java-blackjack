package domain.user;

import java.util.List;
import java.util.Objects;

import domain.card.Card;

public class Player extends User {
	private final String name;

	public Player(String name) {
		validEmptyAndNull(name);
		this.name = name;
	}

	private void validEmptyAndNull(String name) {
		if (Objects.isNull(name) || name.isEmpty()) {
			throw new IllegalArgumentException("이름에 빈값이 들어갈 수 없습니다.");
		}

	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Player player = (Player)o;
		return Objects.equals(name, player.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public List<Card> getInitialCard() {
		return cards.getCards().subList(0, 2);
	}

	@Override
	public String getName() {
		return this.name;
	}
}
