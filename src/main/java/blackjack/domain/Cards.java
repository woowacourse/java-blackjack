package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cards {
	private final List<Card> cards = new ArrayList<>();
	private static final int BUST_THRESHOLD = 21;
	private static final int ANOTHER_ACE_SCORE = 1;
	private int score = 0;

	public void addCard(Card card) {
		cards.add(card);
		calculateScore();
	}

	private void calculateScore() {
		int score = 0;
		for (Card card : cards) {
			score += calculateOneCard(card, score);
		}
		this.score = score;
	}

	private int calculateOneCard(Card card, int score) {
		if (!card.isAce()) {
			return card.getNumber();
		}
		if (score + card.getNumber() > BUST_THRESHOLD) {
			return ANOTHER_ACE_SCORE;
		}
		return Number.ACE.getValue();
	}

	public Card getRandomCard() {
		return cards.get(new Random().nextInt(cards.size()));
	}

	public List<Card> getCards() {
		return this.cards;
	}

	public int getScore() {
		return score;
	}
}
