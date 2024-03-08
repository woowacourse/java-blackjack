package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardHand {
	private static final int ACE_BONUS_SCORE = 10;
	private static final int BLACKJACK_SCORE = 21;

	private final List<Card> cards;

	private CardHand(List<Card> cards) {
		this.cards = new ArrayList<>(cards);
	}

	public static CardHand createEmpty() {
		return new CardHand(new ArrayList<>());
	}

	public static CardHand from(List<Card> cards) {
		return new CardHand(cards);
	}

	public void add(Card card) {
		cards.add(card);
	}

	public void add(List<Card> cards) {
		this.cards.addAll(cards);
	}

	public int calculateScore() {
		int totalScore = cards.stream()
			.map(Card::getScore)
			.reduce(0, Integer::sum);

		return calculateAceScore(totalScore);
	}

	private int calculateAceScore(final int totalScore) {
		boolean hasAceCard = cards.stream()
			.anyMatch(card -> card.isRank(Rank.ACE));

		if (hasAceCard && totalScore + ACE_BONUS_SCORE <= BLACKJACK_SCORE) {
			return totalScore + ACE_BONUS_SCORE;
		}
		return totalScore;
	}

	public boolean isBurst() {
		return calculateScore() > BLACKJACK_SCORE;
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}

	public boolean isScoreLessOrEqual(int value) {
		int score = calculateScore();
		return value >= score;
	}
}
