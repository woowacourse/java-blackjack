package domain;

public class Player {
	private final String name;
	private final Participant participant;

	public Player(String name) {
		this.participant = new Participant();
		this.name = name;
	}

	public Player(final Participant participant) {
		this.participant = participant;
		this.name = "";
	}

	public boolean isPickCard() {
		return participant.calculateAllScore() <= 21;
	}

	public void pickCardOnFirstHandOut(final Deck deck) {
		participant.pickCardOnFirstHandOut(deck);
	}

	public void pickCard(final Deck deck) {
		participant.pickCard(deck);
	}

	public void duel(final Participant other) {
		participant.duel(other);
	}

	public String getName() {
		return name;
	}

	public Participant getParticipant() {
		return participant;
	}
}
