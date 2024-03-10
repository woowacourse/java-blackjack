package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;

public class Player extends Gamer {
	private final Name name;

	private Player(final String name, final CardHand cardHand) {
		super(cardHand);
		this.name = new Name(name);
	}

	public static Player newInstance(final String name) {
		return new Player(name, CardHand.createEmpty());
	}

	public void receiveCard(final Card card) {
		cardHand.add(card);
	}

	public boolean isBust() {
		return cardHand.isBurst();
	}

	public String getName() {
		return name.getValue();
	}
}
