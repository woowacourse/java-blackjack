package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Cards {
	private static final int BLACKJACK_SCORE = 21;
	private static final int BUST_SCORE = 22;
	private static final int ACE_ADDITIONAL_SCORE = 10;
	private static final int BLACKJACK_CARD_SIZE = 2;
	private static final String NOT_EXIST_CARD_EXCEPTION_MESSAGE = "카드가 없습니다.";
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
		int result = cards.stream()
			.map(Card::getTypeScore)
			.reduce(Integer::sum)
			.orElseThrow(() -> new NoSuchElementException(NOT_EXIST_CARD_EXCEPTION_MESSAGE));

		if (containsAce() && result + ACE_ADDITIONAL_SCORE <= BLACKJACK_SCORE) {
			result += ACE_ADDITIONAL_SCORE;
		}
		return result;
	}

	private boolean containsAce() {
		return cards.stream()
			.anyMatch(Card::isAce);
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
