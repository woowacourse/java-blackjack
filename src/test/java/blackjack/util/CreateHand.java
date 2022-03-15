package blackjack.util;

import blackjack.domain.card.Card;
import blackjack.domain.role.Hand;

public class CreateHand {

	public static Hand create(Card... cards) {
		final Hand hand = new Hand();
		for (Card card : cards) {
			hand.addCard(card);
		}
		return hand;
	}
}
