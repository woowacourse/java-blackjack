package blackjack.domain.user;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public class Dealer extends User {
	public static final String NAME = "Dealer";
	public static final int DEALER_DRAWABLE_MAX_SCORE = 16;

	Dealer(String name, Hand hand) {
		super(name, hand);
	}

	public Dealer(String name) {
		super(name);
	}

	public static Dealer valueOf(String name, List<Card> cards) {
		Hand hand = new Hand();
		hand.add(cards);

		return new Dealer(name, hand);
	}

	@Override
	public boolean canDraw() {
		return hand.calculateScore().isLowerThan(DEALER_DRAWABLE_MAX_SCORE);
	}

	@Override
	public List<Card> getInitialDealtHand() {
		return getHand().subList(0, 1);
	}
}
