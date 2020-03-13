package domain.user;

import java.util.List;
import java.util.Objects;

import domain.card.Card;

public class Player extends User {
	private static final int INITIAL_START_INDEX = 0;
	private static final int INITIAL_FROM_INDEX = INITIAL_START_INDEX;
	private static final int INITIAL_TO_INDEX = 2;

	private Player(Name name) {
		super(name);
	}

	public static Player valueOf(String name) {
		return new Player(new Name(name));
	}

	@Override
	public List<Card> getInitialCard() {
		return cards.getCards().subList(INITIAL_FROM_INDEX, INITIAL_TO_INDEX);
	}

	@Override
	public String getName() {
		return name.getName();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Player that = (Player)object;
		return Objects.equals(this.name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
