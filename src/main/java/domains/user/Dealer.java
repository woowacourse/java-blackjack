package domains.user;

import domains.card.Deck;

public class Dealer extends User {
	private static final int DEALER_HIT_POINT = 16;
	private boolean hitFlag = false;

	public Dealer(Deck deck) {
		this.hands = new Hands(deck);
	}

	public Dealer(Hands hands) {
		this.hands = hands;
	}

	public void hitOrStay(Deck deck){
		if (this.hands.score() <= DEALER_HIT_POINT) {
			hit(deck);
			hitFlag = true;
		}
	}

	public boolean didHit(){
		return hitFlag;
	}
}
