package domain.user;

import java.util.List;
import java.util.Objects;

import domain.card.Card;

public class Player extends User {
	private static final int INITIAL_START_INDEX = 0;
	private static final int INITIAL_FROM_INDEX = INITIAL_START_INDEX;
	private static final int INITIAL_TO_INDEX = 2;

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
		return cards.getCards().subList(INITIAL_FROM_INDEX, INITIAL_TO_INDEX);
	}

	@Override
	public String getName() {
		return this.name;
	}
}
