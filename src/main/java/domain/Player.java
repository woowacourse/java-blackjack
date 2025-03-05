package domain;

public class Player {
	private final Participant participant;

	public Player() {
		this.participant = new Participant();
	}

	public Player(final CardHand hand) {
		this.participant = new Participant(hand);
	}

	public boolean isPickCard() {
		return participant.calculateAllScore() <= 21;
	}

	public void pickCard(final Deck deck) {
		participant.pickCard(deck);
	}

	public int calculateAllScore() {
		return participant.calculateAllScore();
	}

	public void duel(final Participant other) {
		participant.duel(other);
	}
}
