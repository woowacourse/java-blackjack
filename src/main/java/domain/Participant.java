package domain;

public class Participant {
	private final CardHand hand;
	private final DuelHistory duelHistory;

	public Participant() {
		hand = new CardHand();
		duelHistory = new DuelHistory();
	}

	public Participant(final CardHand hand) {
		this.hand = hand;
		this.duelHistory = new DuelHistory();
	}

	public Participant(final CardHand hand, final DuelHistory duelHistory) {
		this.hand = hand;
		this.duelHistory = duelHistory;
	}

	public void pickCard(final Deck deck) {
		final Card card = deck.pickCard();
		hand.add(card);
	}

	public int calculateAllScore() {
		return hand.calculateAllScore();
	}

	public void duel(final Participant other) {
		final int score = this.calculateAllScore();
		final int otherScore = other.calculateAllScore();
		if (score <= 21 && otherScore > 21) {
			duelHistory.write(true);
			other.duelHistory.write(false);
			return;
		}
		if (score > 21 && otherScore <= 21) {
			duelHistory.write(false);
			other.duelHistory.write(true);
			return;
		}
		final boolean duelResult = this.calculateAllScore() > other.calculateAllScore();
		duelHistory.write(duelResult);
		other.duelHistory.write(!duelResult);
	}

	public CardHand getHand() {
		return hand;
	}

	public DuelHistory getDuelHistory() {
		return duelHistory;
	}
}

