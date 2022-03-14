package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cards {
	private static final int BUST_THRESHOLD = 21;
	private static final int ACE_SPECIAL_SCORE = 11;
	private static final int INIT_DISTRIBUTE_AMOUNT = 2;
	private final List<Card> cards;
	private int score = 0;

	public Cards(List<Card> cards) {
		this.cards = cards;
	}

	public Cards() {
		this(new ArrayList<>());
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	private void calculateScore() {
		int aceCount = (int) cards.stream().filter(Card::isAce)
			.count();
		int score = cards.stream()
			.filter(card -> !card.isAce())
			.mapToInt(Card::getScore).sum() + aceCount;
		for (int i = 0; i < aceCount; i++) {
			score += calculateOneAce(score);
		}
		this.score = score;
	}

	private int calculateOneAce(int score) {
		int diff = ACE_SPECIAL_SCORE - CardDenomination.ACE.getLetterScore();
		if (score + diff <= BUST_THRESHOLD) {
			return diff;
		}
		return 0;
	}

	public Card getRandomCard() {
		return cards.get(new Random().nextInt(cards.size()));
	}

	public List<Card> getCards() {
		return this.cards;
	}

	public int getScore() {
		calculateScore();
		return score;
	}

	public boolean isBust() {
		return getScore() > BUST_THRESHOLD;
	}

	public boolean isBlackJack() {
		return this.cards.size() == INIT_DISTRIBUTE_AMOUNT && this.getScore() == BUST_THRESHOLD;
	}
}
