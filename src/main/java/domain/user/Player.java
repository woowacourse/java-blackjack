package domain.user;

import java.util.List;
import java.util.Objects;

import domain.card.Card;

public class Player extends User {
	private final String name;

	public Player(String name) {
		this.name = name;
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
