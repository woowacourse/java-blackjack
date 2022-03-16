package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cards {
	private static final int BUST_THRESHOLD = 21;
	private static final int INIT_DISTRIBUTE_AMOUNT = 2;
	private static final int ACE_SCORE_DIFFERENCE = 10;
	private final List<Card> cards;

	public Cards(List<Card> cards) {
		this.cards = cards;
	}

	public Cards() {
		this(new ArrayList<>());
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	private int calculateScore() {
		int score = cards.stream()
			.mapToInt(Card::getScore)
			.sum();
		if (cards.stream().anyMatch(Card::isAce) && score + ACE_SCORE_DIFFERENCE <= BUST_THRESHOLD) {
			return score + ACE_SCORE_DIFFERENCE;
		}
		return score;
	}

	public Card getRandomCard() {
		return cards.get(new Random().nextInt(cards.size()));
	}

	public List<Card> getCards() {
		return this.cards;
	}

	public int getScore() {
		return calculateScore();
	}

	public boolean isBust() {
		return getScore() > BUST_THRESHOLD;
	}

	public boolean isBlackJack() {
		return this.cards.size() == INIT_DISTRIBUTE_AMOUNT && this.getScore() == BUST_THRESHOLD;
	}
}
