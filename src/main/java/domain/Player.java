package domain;

public class Player {
	private final String name;
	private final Participant participant;
	private boolean isWinDuel;

	public Player(final String name) {
		this.participant = new Participant();
		this.name = name;
		isWinDuel = false;
	}

	public Player(final Participant participant) {
		this.participant = participant;
		this.name = "";
		isWinDuel = false;
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
		final int score = participant.calculateAllScore();
		final int otherScore = other.calculateAllScore();
		if (score > 21) {
			return;
		}
		if (otherScore > 21) {
			isWinDuel = true;
			return;
		}
		isWinDuel = score > otherScore;
	}

	public boolean isWinDuel() {
		return isWinDuel;
	}

	public String getName() {
		return name;
	}

	public Participant getParticipant() {
		return participant;
	}
}
