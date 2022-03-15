package blackjack.domain.participant;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Participant {
	private static final int MAX_SCORE = 21;

	private final String name;
	private final Cards cards;

	public Participant(String name) {
		this.name = name;
		this.cards = new Cards();
	}

	public void receiveCard(Card card) {
		cards.addCard(card);
	}

	public Boolean isOverMaxScore() {
		return getScore() > MAX_SCORE;
	}

	abstract public boolean shouldHit();

	public int getScore() {
		return cards.sum();
	}

	public String getName() {
		return name;
	}

	public List<Card> getCards() {
		return this.cards.getCards();
	}
}
