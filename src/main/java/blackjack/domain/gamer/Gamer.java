package blackjack.domain.gamer;

import java.util.Collections;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

abstract class Gamer {
	protected final Cards cards;

	protected Gamer(final Cards cards) {
		this.cards = cards;
	}

	public void receiveInitCards(final List<Card> cards) {
		this.cards.addAll(cards);
	}

	public void receiveCard(final Card card) {
		cards.add(card);
	}

	public boolean isBust() {
		return cards.isBurst();
	}

	public int getScore() {
		return cards.calculateScore();
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards.getCards());
	}
}
