package blackjack.domain.participant;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Participant {
	private static final int MAX_SCORE = 21;

	private final Name name;
	private final Cards cards;

	public Participant(Name name) {
		this.name = name;
		this.cards = new Cards();
	}

	public void receiveCard(Card card) {
		cards.addCard(card);
	}

	public Boolean isBust() {
		return getScore() > MAX_SCORE;
	}

	public Boolean isBlackjack() {
		return cards.size() == 2 && getScore() == 21;
	}

	abstract public int getScore();

	public List<String> getCardNames() {
		return cards.getCardNames();
	}

	public String getName() {
		return name.getName();
	}

	public Cards getCards() {
		return this.cards;
	}
}
