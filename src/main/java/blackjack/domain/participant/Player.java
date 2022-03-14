package blackjack.domain.participant;

import java.util.Objects;

public class Player extends Participant {

	public Player(String name) {
		super(name);
	}

	public static Player copy(Player original) {
		Player copy = new Player(original.name);
		copy.myCards = original.getMyCards();
		return copy;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Player player = (Player)o;
		return name.equals(player.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
