package domain.card;

import static java.util.Comparator.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards {
	private static final int MAXIMUM_SCORE = 21;
	private static final int BLACKJACK_CARD_SIZE = 2;
	private static final String OUT_OF_CARD_SIZE_EXCEPTION_MESSAGE = "인자의 값이 카드 갯수보다 큽니다.";

	private final List<Card> cards;

	public Cards(List<Card> cards) {
		this.cards = new ArrayList<>(Objects.requireNonNull(cards));
	}

	public void add(Card card) {
		cards.add(Objects.requireNonNull(card));
	}

	public boolean isBlackjack() {
		return cards.size() == BLACKJACK_CARD_SIZE && calculateScore() == MAXIMUM_SCORE;
	}

	public boolean isBust() {
		return calculateScore() > MAXIMUM_SCORE;
	}

	public int calculateScore() {
		return reviseScore(sumTotalDefaultScore());
	}

	private int sumTotalDefaultScore() {
		return cards.stream()
			.mapToInt(Card::getScore)
			.sum();
	}

	private int reviseScore(int defaultScore) {
		return cards.stream()
			.map(Card::getSymbol)
			.filter(Symbol::hasBonusScore)
			.sorted(comparingInt(Symbol::getBonusScore).reversed())
			.reduce(defaultScore, this::appendBonusScore, Integer::sum);
	}

	private int appendBonusScore(int score, Symbol symbol) {
		return score + symbol.calculateBonusScore(score, MAXIMUM_SCORE);
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}

	public List<Card> getSubList(int length) {
		validateArgument(length);
		return Collections.unmodifiableList(cards.subList(0, length));
	}

	private void validateArgument(int length) {
		if (cards.size() < length) {
			throw new IllegalArgumentException(OUT_OF_CARD_SIZE_EXCEPTION_MESSAGE);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Cards that = (Cards)object;
		return Objects.equals(this.cards, that.cards);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cards);
	}

	@Override
	public String toString() {
		return "Cards{" +
			"cards=" + cards +
			'}';
	}
}
