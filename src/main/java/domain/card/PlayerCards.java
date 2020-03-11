package domain.card;

import domain.card.cardfactory.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerCards {
	public static final int ACE_SCORE = 1;
	public static final int MAX_SCORE = 21;
	public static final int ACE_BONUS = 10;
	public static final int FIRST_CARD_INDEX = 0;
	public static final String DELIMITER = ", ";

	private final List<Card> playerCards = new ArrayList<>();

	public void add(Card card) {
		playerCards.add(card);
	}

	public void addAll(List<Card> cards) {
		playerCards.addAll(cards);
	}

	public int calculateScore() {
		int score = 0;
		boolean containAce = false;
		for (Card card : playerCards) {
			if (card.getScore() == ACE_SCORE) {
				containAce = true;
			}
			score += card.getScore();
		}
		return calculateFinalScore(score, containAce);
	}

	private int calculateFinalScore(int score, boolean containAce) {
		if (containAce && score + ACE_BONUS <= MAX_SCORE) {
			return score + ACE_BONUS;
		}
		return score;
	}

	public String toStringOneCard() {
		return playerCards.get(FIRST_CARD_INDEX).toString();
	}

	@Override
	public String toString() {
		List<String> cardNames = playerCards.stream()
				.map(Card::toString)
				.collect(Collectors.toList());
		return String.join(DELIMITER, cardNames);
	}
}
