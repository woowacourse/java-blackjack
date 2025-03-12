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
		this.cards.addAll(cards);
	}

	public Score calculateAllScore(final Score bustScore) {
		Score score = Score.from(0);

		for (final Card card : cards) {
			score = card.sumNumber(score);
		}

		return Rank.ifOverThanBustScoreAceIsMIN(score, calculateAceCount(), bustScore);
	}

	private Count calculateAceCount() {
		return new Count((int)cards.stream()
			.filter(card -> card.isMatchNumber(Rank.ACE))
			.count());
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}
}
