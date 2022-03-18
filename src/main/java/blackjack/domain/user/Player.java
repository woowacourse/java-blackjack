package blackjack.domain.user;

import java.util.Objects;

import blackjack.domain.card.Card;
import blackjack.domain.card.DeckStrategy;

public class Player extends Gamer {

	private final Money money;

	public Player(final String name, final int money, final DeckStrategy deck) {
		super(name, deck);
		this.money = new Money(money);
	}

	@Override
	public void addCard(Card card) {
		changeState(this.state.draw(card));
	}

	public double getMoney() {
		return money.getMoney();
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
