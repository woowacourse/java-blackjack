package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.Collections;
import java.util.List;

public final class Dealer extends AbstractPlayer {
	private Dealer() {
		super(new Name(DEALER_NAME));
	}

	public static Dealer dealer() {
		return new Dealer();
	}

	@Override
	public List<Card> getStartHand() {
		return Collections.singletonList(getHand().getHand().get(0));
	}

	@Override
	public boolean canReceiveCard() {
		return computeScore().isUnder(MINIMUM_NUMBER_TO_DEALER_STAY);
	}
}
