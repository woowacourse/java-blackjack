package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards {
	private static final int INITIAL_CARDS_SIZE = 2;

	private final List<Card> cards;

	public Cards(List<Card> cards) {
		this.cards = cards;
	}

	public void add(Card card) {
		cards.add(card);
	}

	public List<Card> toList() {
		return Collections.unmodifiableList(cards);
	}

	public boolean isNotInitialSize() {
		return cards.size() != INITIAL_CARDS_SIZE;
	}

	public boolean hasAce() {
		return cards.stream()
				.anyMatch(Card::isAce);
	}

	public int getPoint() {
		return cards.stream()
				.mapToInt(Card::getPoint)
				.sum();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cards cards1 = (Cards) o;
		return Objects.equals(cards, cards1.cards);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cards);
	}
}
