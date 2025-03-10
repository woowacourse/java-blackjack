package domain;

import domain.constant.DuelResult;

public class Dealer {

	private final Participant participant;

	public Dealer() {
		this.participant = new Participant();
	}

	public Dealer(final CardHand hand) {
		this.participant = new Participant(hand);
	}

	public Dealer(final Participant participant) {
		this.participant = participant;
	}

	public boolean isPickCard(final int bustScore, final int dealerPickCardScoreMax) {
		return participant.calculateAllScore(bustScore) <= dealerPickCardScoreMax;
	}

	public void pickCardOnFirstHandOut(final Deck deck) {
		participant.pickCardOnFirstHandOut(deck);
	}

	public void pickCard(final Deck deck) {
		participant.pickCard(deck);
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
