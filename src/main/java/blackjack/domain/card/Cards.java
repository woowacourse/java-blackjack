package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

	private static final int ACE_AS_ELEVEN = 10;
	private static final int MAX_SCORE = 21;
	private static final int MATCH_BLACKJACK_SIZE = 2;

	private final List<Card> cards;

	public Cards() {
		this.cards = new ArrayList<>();
	}

	public Cards(List<Card> cards) {
		this.cards = cards;
	}

	public Cards add(Card card) {
		List<Card> newCards = new ArrayList<>(cards);
		newCards.add(card);
		return new Cards(newCards);
	}

	public boolean isBust() {
		return sum() > MAX_SCORE;
	}

	private int sum() {
		return cards.stream()
				.mapToInt(Card::getScore)
				.sum();
	}

	public int getScore() {
		int score = sum();
		if (hasAce() && score + ACE_AS_ELEVEN <= MAX_SCORE) {
			return score + ACE_AS_ELEVEN;
		}
		return score;
	}

	public boolean hasAce() {
		return cards.stream()
				.anyMatch(Card::isAce);
	}

	public boolean isBlackjack() {
		return getScore() == MAX_SCORE && cards.size() == MATCH_BLACKJACK_SIZE;
	}

	public int getSize() {
		return cards.size();
	}

	public List<Card> getCards() {
		return new ArrayList<>(cards);
	}
}
