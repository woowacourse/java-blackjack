package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.GameScore;
import value.Count;

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

	public GameScore calculateAllScore(final GameScore bustGameScore) {
		GameScore gameScore = GameScore.from(0);

		for (final Card card : cards) {
			gameScore = card.sumNumber(gameScore);
		}

		return Rank.ifOverThanBustScoreAceIsMIN(gameScore, calculateAceCount(), bustGameScore);
	}

	private Count calculateAceCount() {
		return Count.from((int)cards.stream()
			.filter(card -> card.isMatchNumber(Rank.ACE))
			.count());
	}

	public Count calculateCardCount() {
		return Count.from(cards.size());
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}
}
