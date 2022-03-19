package blackjack.domain.user;

import java.util.Objects;

import blackjack.domain.card.Deck;

public class Player extends Gamer {

	private final Money money;

	public Player(final String name, final double money, final Deck deck) {
		super(name, deck);
		this.money = new Money(money);
	}

	public Money getMoney() {
		return this.money;
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
