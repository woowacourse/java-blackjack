package domains.user;

import domains.card.Deck;

public class User {
	Hands hands;
	private boolean burst = false;

	public void hit(Deck deck) {
		hands.draw(deck);
	}

	public boolean checkBurst() {
		if (hands.isBurst()) {
			this.burst = true;
		}
		return this.burst;
	}

	public boolean isBurst() {
		return burst;
	}

	public int handSize() {
		return hands.size();
	}

	public int score() {
		return hands.score();
	}

	public String getHandsWords() {
		return hands.toString();
	}
}
