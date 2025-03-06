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

	public void pickCardOnFirstHandOut(final Deck deck) {
		pickCard(deck);
		pickCard(deck);
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
			duelHistory.write(1);
			other.duelHistory.write(-1);
			return;
		}
		if (score > 21 && otherScore <= 21) {
			duelHistory.write(-1);
			other.duelHistory.write(1);
			return;
		}
		if (score > 21 && otherScore > 21) {
			duelHistory.write(0);
			other.duelHistory.write(0);
			return;
		}
		final int duelResult = this.calculateAllScore() - other.calculateAllScore();
		duelHistory.write(duelResult);
		other.duelHistory.write(duelResult * -1);
	}

	public CardHand getCardHand() {
		return hand;
	}

	public DuelHistory getDuelHistory() {
		return duelHistory;
	}
}

