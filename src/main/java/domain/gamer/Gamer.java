package domain.gamer;

import java.util.List;

import domain.card.Card;
import domain.card.CardHand;

public class Gamer {
	protected final CardHand cardHand;

	protected Gamer(CardHand cardHand) {
		this.cardHand = cardHand;
	}

	public void receiveInitCards(List<Card> cards) {
		cardHand.add(cards);
	}

	public int getScore() {
		return cardHand.calculateScore();
	}

	public List<Card> getCardHand() {
		return cardHand.getCards();
	}
}
