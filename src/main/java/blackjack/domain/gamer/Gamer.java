package blackjack.domain.gamer;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;

public class Gamer {
	protected final CardHand cardHand;

	protected Gamer(final CardHand cardHand) {
		this.cardHand = cardHand;
	}

	public void receiveInitCards(final List<Card> cards) {
		cardHand.addAll(cards);
	}

	public int getScore() {
		return cardHand.calculateScore();
	}

	public List<Card> getCardHand() {
		return cardHand.getCards();
	}
}
