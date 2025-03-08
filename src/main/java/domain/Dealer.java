package domain;

public class Dealer {
	private static final int DEALER_PICK_CARD_CONDITION_SCORE = 16;

	private final Participant participant;

	public Dealer() {
		this.participant = new Participant();
	}

	public Dealer(final CardHand hand) {
		this.participant = new Participant(hand);
	}

	public boolean isPickCard() {
		return participant.calculateAllScore() <= DEALER_PICK_CARD_CONDITION_SCORE;
	}

	public void pickCardOnFirstHandOut(final Deck deck) {
		participant.pickCardOnFirstHandOut(deck);
	}

	public void pickCard(final Deck deck) {
		participant.pickCard(deck);
	}

	public void startDuel(final Player player) {
		player.duel(participant);
	}

	public Participant getParticipant() {
		return participant;
	}
}
