package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
	private List<Card> cards;

	public Cards() {
		this.cards = new ArrayList<>();
	}

	public List<Card> getValue() {
		return Collections.unmodifiableList(cards);
	}

	public int getSize() {
		return cards.size();
	}

	public int getScore() {
		return score().getValue();
	}

	public void add(Card card) {
		cards.add(card);
	}

	public boolean isBlackJack() {
		return score().isBlackjack(cards.size());
	}

	public boolean isBust() {
		return score().isBust();
	}

	public boolean isScoreGreaterThan(int score) {
		return score().isGreaterThan(score);
	}

	private Score score() {
		Score score = Score.ZERO;
		for (Card card : cards) {
			score = score.add(card.getPoint());
		}
		if (hasAce()) {
			score = score.addAceBonusIfNotBust();
		}
		return score;
	}

	private boolean hasAce() {
		return cards.stream().anyMatch(Card::isAce);
	}
}