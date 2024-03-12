package blackjack.domain.gamer;

import blackjack.domain.BlackjackConstants;

public class Dealer extends BlackjackGamer {

	private static final String DEFAULT_DEALER_NAME = "딜러";

	public Dealer() {
		super(new Name(DEFAULT_DEALER_NAME));
	}

	@Override
	public boolean canReceiveCard() {
		return getScore() < BlackjackConstants.DEALER_MINIMUM_VALUE.getValue();
	}
}
