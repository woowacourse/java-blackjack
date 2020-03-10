package blackjack.player;

import java.util.Objects;

import blackjack.player.card.CardBundle;

public class User extends Player {
	private final String name;

	public User(CardBundle cardBundle, String name) {
		super(cardBundle);
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User)o;
		return Objects.equals(name, user.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
