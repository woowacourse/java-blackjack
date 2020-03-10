package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Cards {
	private static final int BLACKJACK_SCORE = 21;
	private static final int ACE_ADDITIONAL_SCORE = 10;
	private static final int BLACKJACK_CARD_SIZE = 2;

	private final List<Card> cards;

	public Cards(List<Card> cards) {
		this.cards = new ArrayList<>(cards);
	}

	public void addAll(ArrayList<Card> cards) {
		this.cards.addAll(cards);
	}

	public boolean isBlackjack() {
		return cards.size() == BLACKJACK_CARD_SIZE && calculateScore() == BLACKJACK_SCORE;
	}

	public int calculateScore() {
		int result = cards.stream()
			.map(Card::getScore)
			.reduce(Integer::sum)
			.orElseThrow(() -> new NoSuchElementException("카드가 없습니다."));

		if (containsAce() && result + ACE_ADDITIONAL_SCORE <= BLACKJACK_SCORE) {
			result += ACE_ADDITIONAL_SCORE;
		}
		return result;
	}

	private boolean containsAce() {
		return cards.stream()
			.anyMatch(Card::isAce);
	}
}
