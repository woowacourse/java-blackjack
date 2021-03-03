package blakcjack.domain.participant;

import blakcjack.domain.card.Card;
import blakcjack.domain.name.Name;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Participant {
	protected final Name name;
	protected final List<Card> cards = new ArrayList<>();

	protected Participant(final Name name) {
		this.name = name;
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}

	public void receiveCard(Card card) {
		cards.add(card);
	}

	public String getName() {
		return name.getName();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Participant that = (Participant) o;
		return Objects.equals(name, that.name) && Objects.equals(cards, that.cards);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, cards);
	}
}
