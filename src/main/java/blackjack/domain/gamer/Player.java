package blackjack.domain.gamer;

import blackjack.domain.card.Cards;

public final class Player extends Gamer {
	private final Name name;

	public Player(final String name) {
		this(new Name(name), Cards.createEmpty());
	}

	public Player(final Name name, final Cards cards) {
		super(cards);
		this.name = name;
	}

	public String getName() {
		return name.getValue();
	}
}
