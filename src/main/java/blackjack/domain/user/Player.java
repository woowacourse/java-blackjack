package blackjack.domain.user;

import java.util.Objects;

public class Player extends Gamer {
	private final Name name;

	public Player(String name) {
		this.name = new Name(name);
	}

	public String getName() {
		return this.name.getName();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Player player = (Player)o;
		return name.equals(player.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
