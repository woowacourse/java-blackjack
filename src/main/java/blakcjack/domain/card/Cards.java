package blakcjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards {
	public static final int BLACKJACK_VALUE = 21;
	private static final int ACE_ADDITIONAL_VALUE = 10;
	private static final int FIRST_CARD_INDEX = 0;
	private static final int SECOND_CARD_INDEX = 1;
	private static final int BLACKJACK_CARD_COUNT = 2;

	private final List<Card> cards = new ArrayList<>();

	public List<Card> toList() {
		return Collections.unmodifiableList(cards);
	}

	public void add(final Card card) {
		cards.add(card);
	}

	public Cards getFirstTwoCards() {
		final Cards hand = getFirstCard();
		hand.add(cards.get(SECOND_CARD_INDEX));
		return hand;
	}

	public Cards getFirstCard() {
		final Cards hand = new Cards();
		hand.add(cards.get(FIRST_CARD_INDEX));
		return hand;
	}

	public boolean isBlackjack() {
		return haveOnlyTwoCards() && haveBlackjackScore();
	}

	private boolean haveOnlyTwoCards() {
		return cards.size() == BLACKJACK_CARD_COUNT;
	}

	private boolean haveBlackjackScore() {
		return calculateScore() == BLACKJACK_VALUE;
	}

	public boolean isBust() {
		return BLACKJACK_VALUE < calculateScore();
	}

	public boolean isScoreLowerThanBlackjackScore() {
		return calculateScore() < BLACKJACK_VALUE;
	}

	public int calculateScore() {
		int score = calculateMinimumPossibleScore();
		int aceCount = calculateAceCount();

		while (hasNextPossibleScore(aceCount, score)) {
			score += ACE_ADDITIONAL_VALUE;
			aceCount--;
		}
		return score;
	}

	private int calculateMinimumPossibleScore() {
		return cards.stream()
				.mapToInt(Card::getCardNumberValue)
				.sum();
	}

	private int calculateAceCount() {
		return (int) cards.stream()
				.filter(Card::isAce)
				.count();
	}

	private boolean hasNextPossibleScore(final int aceCount, final int score) {
		return 0 < aceCount && (score + ACE_ADDITIONAL_VALUE) <= BLACKJACK_VALUE;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Cards cards1 = (Cards) o;
		return Objects.equals(cards, cards1.cards);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cards);
	}
}
