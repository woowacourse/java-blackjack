package blackjack.domain;

import java.util.Objects;

public class Player extends Gamer {

	public Player(String name) {
		super(name);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Player player = (Player)o;
		return this.getName().equals(player.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName());
	}
}
