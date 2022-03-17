package blackjack.domain.participant;

import blackjack.domain.card.Card;

public class Dealer extends Participant {
	private static final String DEALER_NAME = "딜러";
	private static final int DEALER_HIT_CARD_STANDARD = 16;

	public Dealer() {
		super(Name.from(DEALER_NAME));
	}

	public Card getFirstCard() {
		return super.getCards().pickFirstCard();
	}

	@Override
	public boolean shouldHit() {
		return getScore() > DEALER_HIT_CARD_STANDARD;
	}
}
