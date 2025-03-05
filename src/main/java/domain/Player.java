package domain;

public class Player {
	private final String name;
	private final Participant participant;

	public Player() {
		this.participant = new Participant();
		this.name = "";
	}

	public Player(String name) {
		this.participant = new Participant();
		this.name = name;
	}

	public Player(final Participant participant) {
		this.participant = participant;
		this.name = "";
	}

	public Player(final CardHand hand, final String name) {
		this.participant = new Participant(hand);
		this.name = name;
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

	public String getName() {
		return name;
	}
}
