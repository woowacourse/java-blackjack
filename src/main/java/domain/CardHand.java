package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardHand {

	private final List<Card> cards;

	public CardHand() {
		this.cards = new ArrayList<>();
	}

	public CardHand(List<Card> cards) {
		this.cards = new ArrayList<>(cards);
	}

	public void add(final Card card) {
		cards.add(card);
	}

	public int calculateAllScore() {
		int score = 0;

		for (final Card card : cards) {
			score = card.sumNumber(score);
		}

		return Rank.ifOverThanBustScoreAceIsMIN(score, calculateAceCount());
	}

	private int calculateAceCount() {
		return (int)cards.stream()
			.filter(card -> card.isMatchNumber(Rank.ACE))
			.count();
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}
}
