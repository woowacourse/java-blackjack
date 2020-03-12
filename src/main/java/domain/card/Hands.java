package domain.card;

import java.util.ArrayList;
import java.util.List;

/**
 *    손패를 나타내는 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class Hands {
	public static final int BLACKJACK = 21;
	private static final int ACE_TO_ELEVEN = 10;

	private List<Card> cards;

	public Hands() {
		this.cards = new ArrayList<>();
	}

	public int calculateTotalScore() {
		int totalScore = cards.stream()
			.mapToInt(Card::score)
			.sum();

		if (hasAce() && (totalScore + ACE_TO_ELEVEN <= BLACKJACK)) {
			return totalScore + ACE_TO_ELEVEN;
		}
		return totalScore;
	}

	public boolean hasAce() {
		return cards.stream()
			.anyMatch(Card::isAce);
	}

	public void add(Card card) {
		cards.add(card);
	}

	public boolean hasTwoCards() {
		return cards.size() == 2;
	}

	public List<Card> getCards() {
		return cards;
	}
}
