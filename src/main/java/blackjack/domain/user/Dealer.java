package blackjack.domain.user;

import blackjack.domain.card.Card;

public interface Dealer extends Player {
	String NAME = "딜러";
	int MINIMUM_NUMBER_TO_STAY = 17;

	boolean shouldReceiveCard();

	Card getFirstCard();
}
