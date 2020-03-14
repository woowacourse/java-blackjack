package blackjack.domain.user.hand;

import java.util.LinkedList;
import java.util.List;

import blackjack.domain.card.Card;

public class Hand {
	private static final int BLACKJACK_SCORE = 21;

	private final List<Card> cards;

	public Hand() {
		this.cards = new LinkedList<>();
	}

	public void add(Card card) {
		cards.add(card);
	}

	public void add(List<Card> cards) {
		this.cards.addAll(cards);
	}

	public Score calculateScore() {
		Score score = Score.ZERO;

		for (Card card : cards) {
			score = score.add(card);
		}
		return score;
	}

	public Score calculateBustHandledScore() {
		Score score = calculateScore();

		if (score.getScore() > BLACKJACK_SCORE) {
			return Score.ZERO;
		}
		return score;
	}

	public List<Card> getCards() {
		return cards;
	}
}
