package domain;

public class Player {
	private final String name;
	private final Participant participant;

	public Player(final String name) {
		this.participant = new Participant();
		this.name = name;
	}

	public Player(final String name, final Participant participant) {
		this.name = name;
		this.participant = participant;
	}

	public boolean isPickCard(final int bustScore) {
		return participant.calculateAllScore(bustScore) <= bustScore;
	}

	public void pickCardOnFirstHandOut(final Deck deck) {
		participant.pickCardOnFirstHandOut(deck);
	}

	public void pickCard(final Deck deck) {
		participant.pickCard(deck);
	}

	public void duel(final Participant other, final int bustScore) {
		final int score = participant.calculateAllScore(bustScore);
		final int otherScore = other.calculateAllScore(bustScore);
		if (score > bustScore) {
			other.writeWin(participant);
			return;
		}
		if (otherScore > bustScore || score > otherScore) {
			participant.writeWin(other);
			return;
		}

		other.writeWin(participant);
	}

	public String getName() {
		return name;
	}

	public Participant getParticipant() {
		return participant;
	}
}
