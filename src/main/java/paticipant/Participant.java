package paticipant;

import java.util.List;

import card.Card;
import card.CardHand;
import duel.DuelHistory;
import duel.DuelResult;
import game.GameScore;
import value.Count;

public class Participant {
	private static final GameScore BUST_GAME_SCORE = GameScore.from(21);
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

	public GameScore calculateAllScore() {
		return hand.calculateAllScore(BUST_GAME_SCORE);
	}

	public boolean isBust() {
		return calculateAllScore().isGreaterThan(BUST_GAME_SCORE);
	}

	public boolean isBlackjack() {
		return calculateAllScore().equals(BUST_GAME_SCORE) && BLACKJACK_REQUIRE_CARD_COUNT.equals(
			hand.calculateCardCount());
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

