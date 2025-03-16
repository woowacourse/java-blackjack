package paticipant;

import java.util.List;

import card.Card;
import card.CardHand;
import duel.DuelHistory;
import duel.DuelResult;
import value.Count;
import value.Score;

public class Participant {
	private static final Score BUST_SCORE = Score.from(21);
	private static final Count BLACKJACK_REQUIRE_CARD_COUNT = Count.from(2);

	private final CardHand hand;
	private final DuelHistory duelHistory;

	public Participant() {
		hand = new CardHand();
		this.duelHistory = new DuelHistory();
	}

	public Participant(final CardHand hand) {
		this.hand = hand;
		this.duelHistory = new DuelHistory();
	}

	public void addCards(final List<Card> cards) {
		hand.addCards(cards);
	}

	public void writeDuelResult(final DuelResult duelResult) {
		duelHistory.write(duelResult);
	}

	public Score calculateAllScore() {
		return hand.calculateAllScore(BUST_SCORE);
	}

	public boolean isBust() {
		return calculateAllScore().isGreaterThan(BUST_SCORE);
	}

	public boolean isBlackjack() {
		return calculateAllScore().equals(BUST_SCORE) && BLACKJACK_REQUIRE_CARD_COUNT.equals(hand.calculateCardCount());
	}

	public DuelResult calculateDuelResult() {
		return duelHistory.calculateDuelResult();
	}

	public CardHand getCardHand() {
		return hand;
	}

	public DuelHistory getDuelHistory() {
		return duelHistory;
	}
}

