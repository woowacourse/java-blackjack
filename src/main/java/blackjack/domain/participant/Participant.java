package blackjack.domain.participant;

import java.util.Objects;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Participant {

	protected final Name name;
	protected final Cards cards;

	protected Participant(Name name, Cards cards) {
		this.name = name;
		this.cards = cards;
	}

	protected abstract boolean isFinished();

	public boolean isBlackJack() {
		return cards.isBlackJack();
	}

	public boolean isBust() {
		return cards.isBust();
	}

	public void drawCard(Card card) {
		cards.add(card);
	}

	public int compareSum(Participant otherParticipant) {
		return this.cards.sum() - otherParticipant.cards.sum();
	}

	public String getName() {
		return name.getValue();
	}

	public Cards getCards() {
		return cards;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Participant that = (Participant)o;
		return name.equals(that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
