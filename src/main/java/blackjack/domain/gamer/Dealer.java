package blackjack.domain.gamer;

import java.util.List;

import blackjack.domain.BlackjackConstants;
import blackjack.domain.card.Card;

public class Dealer extends BlackjackGamer {

	private static final String DEFAULT_DEALER_NAME = "딜러";

	public Dealer() {
		super(DEFAULT_DEALER_NAME);
	}

	public Dealer(List<Card> cards) {
		super(DEFAULT_DEALER_NAME, cards);
	}

	@Override
	public boolean canReceiveCard() {
		return getScore() < BlackjackConstants.DEALER_MINIMUM_VALUE.getValue();
	}
}
