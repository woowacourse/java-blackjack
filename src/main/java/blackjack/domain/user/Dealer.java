package blackjack.domain.user;

import blackjack.domain.card.Card;

public interface Dealer extends Player {
	String NAME = "딜러";

	int getMinimumNumberToStay();

	boolean shouldReceiveCard();

	Card getFirstCard();
}
