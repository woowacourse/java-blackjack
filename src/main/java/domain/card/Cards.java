package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import domain.ScoreType;

public class Cards {
	private static final int INITIAL_CARDS_SIZE = 2;

	private final List<Card> cards;

	public Cards(List<Card> cards) {
		this.cards = cards;
	}

	public void add(Card card) {
		cards.add(card);
	}

	public boolean isInitialSize() {
		return cards.size() == INITIAL_CARDS_SIZE;
	}

	public boolean isNotInitialSize() {
		return cards.size() != INITIAL_CARDS_SIZE;
	}

	public int getPoint() {
		int point = cards.stream()
			.map(Card::getSymbol)
			.mapToInt(Symbol::getPoint)
			.sum();

		int bonusPoint = cards.stream()
			.map(Card::getSymbol)
			.filter(symbol -> symbol.isPromotable(point))
			.mapToInt(Symbol::getBonusPoint)
			.findFirst()
			.orElse(0);

		int resultPoint = point + bonusPoint;
		return ScoreType.of(resultPoint).getScore(resultPoint);
	}

	public List<Card> toList() {
		return Collections.unmodifiableList(cards);
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
