package domains.user;

import domains.card.Deck;

public class Dealer extends User {
	private static final int DEALER_HIT_POINT = 16;

	public Dealer(Deck deck) {
		this(new Hands(deck));
	}

	public Dealer(Hands hands) {
		this.hands = hands;
		this.blackJack = hands.isBlackJack();
	}

	public boolean isHit() {
		return this.hands.size() == 3;
	}

	public void hitOrStay(Deck deck) {
		if (this.hands.score() <= DEALER_HIT_POINT) {
			hit(deck);
		}
	}
}
