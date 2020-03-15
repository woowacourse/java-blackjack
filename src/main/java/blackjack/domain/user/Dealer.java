package blackjack.domain.user;

import java.util.List;

import blackjack.domain.card.Card;

public class Dealer extends User {
	public static final String NAME = "Dealer";
	public static final int DEALER_DRAWABLE_MAX_SCORE = 16;

	public Dealer(String name) {
		super(name);
	}

	Dealer(String name, List<Card> cards) {
		super(name, cards);
	}

	public static Dealer valueOf(String name, List<Card> cards) {
		return new Dealer(name, cards);
	}

	@Override
	public boolean canDraw() {
		return hand.calculateScore().isLowerThan(DEALER_DRAWABLE_MAX_SCORE);
	}

	@Override
	public List<Card> getInitialHand() {
		return hand.getCards().subList(0, 1);
	}
}
