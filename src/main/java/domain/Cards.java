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

	public void add(Card card) {
		cards.add(card);
	}

	public int getSize() {
		return cards.size();
	}

	public Score getScore() {
		return reviseAceScore(calculateRawScore());
	}

	private Score calculateRawScore() {
		Score score = Score.ZERO;
		for (Card card : cards) {
			score = score.add(card.getPoint());
		}
		return score;
	}

	private Score reviseAceScore(Score score) {
		if (hasAce()) {
			return score.addAceBonusIfNotBust();
		}
		return score;
	}

	private boolean hasAce() {
		return cards.stream().anyMatch(Card::isAce);
	}
}