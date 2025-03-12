package paticipant;

import java.util.List;

import card.Card;
import card.CardHand;
import card.Score;
import duel.DuelHistory;
import duel.DuelResult;

public class Participant {
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

	public Score calculateAllScore(final Score bustScore) {
		return hand.calculateAllScore(bustScore);
	}

	public CardHand getCardHand() {
		return hand;
	}

	public DuelHistory getDuelHistory() {
		return duelHistory;
	}
}

