package blackjack.domain.gamer;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;

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

	public boolean isBust() {
		return cardHand.isBurst();
	}

	@Override
	public String toString() {
		return "Gamer{" +
				"cardHand=" + cardHand +
				'}';
	}

	public List<Card> getCardHand() {
		return cardHand.getCards();
	}
}
