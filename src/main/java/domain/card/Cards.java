package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Cards {
	private static final int BLACKJACK_SCORE = 21;
	private static final int BUST_SCORE = 22;
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
		return cards.size() == BLACKJACK_CARD_SIZE && calculateScore() == BLACKJACK_SCORE;
	}

	public boolean isBust() {
		return calculateScore() >= BUST_SCORE;
	}

	public int calculateScore() {
		List<Card> cards = new ArrayList<>(this.cards);
		cards.sort(Comparator.reverseOrder());

		int totalScore = 0;
		for (Card card : cards) {
			Symbol currentSymbol = card.getSymbol();
			int currentScore = currentSymbol.calculateScore(totalScore, BLACKJACK_SCORE);
			totalScore += currentScore;
		}
		return totalScore;
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
}
