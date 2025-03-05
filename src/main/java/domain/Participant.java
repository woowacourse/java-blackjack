package domain;

public abstract class Participant {
	private final CardHand hand;

	public Participant() {
		hand = new CardHand();
	}

	public Participant(final CardHand hand) {
		this.hand = hand;
	}

	public abstract boolean isPickCard();

	public void pickCard(final Deck deck) {
		final Card card = deck.pickCard();
		hand.add(card);
	}

	public int calculateAllScore() {
		return hand.calculateAllScore();
	}

	public CardHand getHand() {
		return hand;
	}

	public void duel(final Participant loser) {
	}

	public DuelHistory getDuelHistory() {
		return null;
	}
}

