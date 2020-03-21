package domains.user;

import java.util.function.Consumer;

import domains.card.Card;
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

	public void hitOrStay(Deck deck, Consumer<Dealer> printDealerHitCard) {
		if (this.hands.score() <= DEALER_HIT_POINT) {
			hit(deck);
			printDealerHitCard.accept(this);
		}
	}

	public Card openFirstCard() {
		return hands.from(0);
	}
}
