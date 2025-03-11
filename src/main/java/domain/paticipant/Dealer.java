package domain.paticipant;

import java.util.List;

import domain.card.Card;
import domain.duel.DuelResult;

public class Dealer implements PickableCard {

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

	public boolean isPickCard(final int bustScore, final int dealerPickCardScoreMax) {
		return participant.calculateAllScore(bustScore) <= dealerPickCardScoreMax;
	}

	public int calculateAllScore(final int bustScore) {
		return participant.calculateAllScore(bustScore);
	}

	public boolean isBust(final int bustScore) {
		return calculateAllScore(bustScore) > bustScore;
	}

	public void writeDuelResult(final DuelResult duelResult) {
		participant.writeDuelResult(duelResult);
	}

	public Participant getParticipant() {
		return participant;
	}
}
