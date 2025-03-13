package paticipant;

import java.util.List;

import card.Card;
import duel.DuelResult;
import value.Score;

public class Dealer {
	private static final Score DEALER_PICK_CARD_SCORE_MAX = Score.from(16);

	private final Participant participant;

	public Dealer() {
		this.participant = new Participant();
	}

	public Dealer(final Participant participant) {
		this.participant = participant;
	}

	public void addCards(final List<Card> cards) {
		participant.addCards(cards);
	}

	public boolean isPickCard() {
		final Score score = participant.calculateAllScore();
		return score.isLessThan(DEALER_PICK_CARD_SCORE_MAX) || score.equals(DEALER_PICK_CARD_SCORE_MAX);
	}

	public Score calculateAllScore() {
		return participant.calculateAllScore();
	}

	public boolean isBust() {
		return participant.isBust();
	}

	public void writeDuelResult(final DuelResult duelResult) {
		participant.writeDuelResult(duelResult);
	}

	public Participant getParticipant() {
		return participant;
	}
}
