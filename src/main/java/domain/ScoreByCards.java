package domain;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Denomination;

public class ScoreByCards {

	private static final int INITIAL_SCORE = 0;
	private static final int INITIAL_CARD_COUNT = 2;
	private static final int BLACKJACK_SCORE = 21;
	private static final int ACE_SUB_NUMBER = 1;

	private final Cards cards;
	private int score;

	private ScoreByCards(final Cards cards, final int score) {
		this.cards = cards;
		this.score = score;
	}

	public static ScoreByCards empty() {
		return new ScoreByCards(Cards.empty(), INITIAL_SCORE);
	}

	public void add(final Card card) {
		cards.add(card);
	}

	public boolean isBlackJack() {
		if (cards.size() >= INITIAL_CARD_COUNT) {
			return scoreOfFirst(INITIAL_CARD_COUNT) == BLACKJACK_SCORE;
		}
		return false;
	}

	private int scoreOfFirst(int count) {
		int score = 0;
		for (int i = 0; i < count; i++) {
			score += cards.getNumberOf(i);
		}
		return score;
	}

	public boolean isBust() {
		updateScore();
		return score > BLACKJACK_SCORE;
	}

	public int getScore() {
		updateScore();
		return score;
	}

	private void updateScore() {
		final int score = cards.sumCardNumbers();
		final int count = cards.count(Denomination.ACE);
		this.score = modifyScoreByAce(score, count);
	}

	private int modifyScoreByAce(int score, final int count) {
		for (int i = 0; i < count; i++) {
			score = changeToAceSub(score);
		}
		return score;
	}

	private int changeToAceSub(int score) {
		if (score > BLACKJACK_SCORE) {
			score -= Denomination.ACE.getNumber();
			score += ACE_SUB_NUMBER;
		}
		return score;
	}

	public List<Card> getCards() {
		return new ArrayList<>(cards.getCards());
	}
}
