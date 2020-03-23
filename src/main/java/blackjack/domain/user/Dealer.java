package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Score;

import java.util.Collections;
import java.util.List;

public final class Dealer extends AbstractPlayer {
	private Dealer(Hand hand) {
		super(new Name(DEALER_NAME), hand);
	}

	public static Dealer empty() {
		return new Dealer(new Hand());
	}

	public static Dealer of(Hand hand) {
		return new Dealer(hand);
	}

	@Override
	public List<Card> getStartHand() {
		return getHand().getListOfFirstCard();
	}

	@Override
	public boolean canReceiveCard() {
		return getHand().isUnder(Score.of(MINIMUM_NUMBER_TO_DEALER_STAY));
	}
}
