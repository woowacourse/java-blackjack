package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Hands {
	private final List<Card> cards;

	private Hands(List<Card> cards) {
		this.cards = cards;
	}

	public static Hands createEmpty() {
		return new Hands(new ArrayList<>());
	}

	public void add(Card card) {
		cards.add(card);
	}

	public int sumOfCards() {
		return cards.stream()
				.mapToInt(Card::getScore)
				.sum();
	}

	public boolean hasAce() {
		return cards.stream()
				.anyMatch(Card::isAce);
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Hands hands = (Hands) o;
		return Objects.equals(cards, hands.cards);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cards);
	}
}
