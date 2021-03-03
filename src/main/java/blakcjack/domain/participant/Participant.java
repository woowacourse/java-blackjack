package blakcjack.domain.participant;

import blakcjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Participant {
	protected final String name;
	protected final List<Card> cards = new ArrayList<>();

	protected Participant(final String name) {
		this.name = name;
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}

	public void receiveCard(Card card) {
		cards.add(card);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Participant that = (Participant) o;
		return Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
