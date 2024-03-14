package blackjack.domain.gamer;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Gamer {
	protected final Cards cards;

	protected Gamer(final Cards cards) {
		this.cards = cards;
	}

	public void receiveInitCards(final List<Card> cards) {
		this.cards.addAll(cards);
	}

	public int getScore() {
		return cards.calculateScore();
	}

	public List<Card> getCards() {
		return cards.getCards();
	}
}
