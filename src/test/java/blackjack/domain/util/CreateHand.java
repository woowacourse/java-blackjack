package blackjack.domain.util;

import blackjack.domain.Card;
import blackjack.domain.Hand;

public class CreateHand {

	public static Hand create(Card... cards) {
		final Hand hand = new Hand();
		for (Card card : cards) {
			hand.addCard(card);
		}
		return hand;
	}
}
