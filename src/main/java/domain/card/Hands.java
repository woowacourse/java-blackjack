package domain.card;

import java.util.ArrayList;
import java.util.List;

/**
 *    손패를 나타내는 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class Hands {
	public static final int BLACKJACK_SCORE = 21;
	private static final int BLACKJACK_SIZE = 2;
	private static final int BEING_HIGH_NUMBER_OF_ACE = 10;

	private List<Card> cards;

	public Hands() {
		this.cards = new ArrayList<>();
	}

	public boolean isBust() {
		return calculateTotalScore() > Hands.BLACKJACK_SCORE;
	}

	public boolean isBlackjack() {
		return (calculateTotalScore() == BLACKJACK_SCORE) && (cards.size() == BLACKJACK_SIZE);
	}

	public int calculateTotalScore() {
		int totalScore = cards.stream()
			.mapToInt(Card::score)
			.sum();

		if (hasAce() && (totalScore + BEING_HIGH_NUMBER_OF_ACE <= BLACKJACK_SCORE)) {
			return totalScore + BEING_HIGH_NUMBER_OF_ACE;
		}
		return totalScore;
	}

	private boolean hasAce() {
		return cards.stream()
			.anyMatch(Card::isAce);
	}

	public void add(Card card) {
		cards.add(card);
	}

	public List<Card> getCards() {
		return cards;
	}
}
