package domain;

public class Dealer {

	private final Participant participant;

	public Dealer() {
		this.participant = new Participant();
	}

	public Dealer(final CardHand hand) {
		this.participant = new Participant(hand);
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

	public void startDuel(final Player player, final int bustScore) {
		player.duel(participant, bustScore);
	}

	public Participant getParticipant() {
		return participant;
	}
}
