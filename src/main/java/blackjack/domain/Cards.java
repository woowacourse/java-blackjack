package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cards {
	private static final int BUST_THRESHOLD = 21;
	private static final int ACE_SPECIAL_SCORE = 11;
	private final List<Card> cards = new ArrayList<>();
	private int score = 0;

	public void addCard(Card card) {
		cards.add(card);
		calculateScore();
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
		return score;
	}
}
