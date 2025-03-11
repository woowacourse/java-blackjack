package domain.card;

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

	public void addCards(final List<Card> cards) {
		cards.addAll(cards);
	}

	public int calculateAllScore(final int bustScore) {
		int score = 0;

		for (final Card card : cards) {
			score = card.sumNumber(score);
		}

		return Rank.ifOverThanBustScoreAceIsMIN(score, calculateAceCount(), bustScore);
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
