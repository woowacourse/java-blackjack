package domain.user;

import domain.card.Card;
import domain.card.Cards;
import domain.user.strategy.draw.PlayerDrawStrategy;

import java.util.List;
import java.util.Objects;

public class Player extends User {
	private final String name;

	public Player(String name) {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("이름이 빈 문자열입니다.");
		}
		this.name = name;
		drawStrategy = new PlayerDrawStrategy();
	}

	public Player(String name, List<Card> cards) {
		this(name);
		hands = new Cards(cards);
	}

	@Override
	public Cards openInitialCards() {
		return hands;
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

	@Override
	public String toString() {
		return name;
	}
}
