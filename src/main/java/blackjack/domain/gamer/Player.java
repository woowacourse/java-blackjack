package blackjack.domain.gamer;

import blackjack.domain.card.Cards;

public class Player extends Gamer {
	private final Name name;

	private Player(final String name, final Cards cards) {
		super(cards);
		this.name = new Name(name);
	}

	public static Player newInstance(final String name) {
		return new Player(name, Cards.createEmpty());
	}

	public boolean isBust() {
		return cards.isBurst();
	}

	public String getName() {
		return name.getValue();
	}
}
