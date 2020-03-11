package domain.card;

import domain.Rull;
import domain.card.cardfactory.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerCards {
	private static final int FIRST_CARD_INDEX = 0;
	private static final String CARD_DELIMITER = ", ";

	private final List<Card> playerCards = new ArrayList<>();

	public void add(Card card) {
		playerCards.add(card);
	}

	public void addAll(List<Card> cards) {
		playerCards.addAll(cards);
	}

	public int calculateScore() {
		int score = Rull.BASE_SCORE;
		boolean containAce = false;
		for (Card card : playerCards) {
			containAce = isContainAce(containAce, card);
			score += card.getScore();
		}
		return calculateFinalScore(score, containAce);
	}

	private boolean isContainAce(boolean containAce, Card card) {
		if (card.isAce()) {
			containAce = true;
		}
		return containAce;
	}

	private int calculateFinalScore(int score, boolean containAce) {
		if (containAce && score + Rull.ACE_BONUS <= Rull.MAX_SCORE) {
			return score + Rull.ACE_BONUS;
		}
		return score;
	}

	public String toStringOneCard() {
		return playerCards.get(FIRST_CARD_INDEX).toString();
	}

	public String toStringAllCard() {
		List<String> cardNames = playerCards.stream()
				.map(Card::toString)
				.collect(Collectors.toList());
		return String.join(CARD_DELIMITER, cardNames);
	}
}
