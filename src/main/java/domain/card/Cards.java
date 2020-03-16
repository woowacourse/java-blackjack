package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class Cards {
	private static final int BLACKJACK_SCORE = 21;
	private static final int ACE_ADDITIONAL_SCORE = 10;
	private static final String EMPTY_CARDS_MESSAGE = "카드가 없습니다.";

	private final List<Card> cards;

	public Cards(List<Card> cards) {
		this.cards = new ArrayList<>(cards);
	}

	public void addAll(List<Card> cards) {
		this.cards.addAll(cards);
	}

	public int size() {
		return cards.size();
	}

	public int calculateScore() {
		int result = sumScore();
		if (containsAce() && canAddAceHiddenScore(result)) {
			result += ACE_ADDITIONAL_SCORE;
		}
		return result;
	}

	private int sumScore() {
		return cards.stream()
			.map(Card::getTypeScore)
			.reduce(Integer::sum)
			.orElseThrow(() -> new NoSuchElementException(EMPTY_CARDS_MESSAGE));
	}

	private boolean containsAce() {
		return cards.stream()
			.anyMatch(Card::isAce);
	}

	private boolean canAddAceHiddenScore(int result) {
		return result + ACE_ADDITIONAL_SCORE <= BLACKJACK_SCORE;
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}
}
