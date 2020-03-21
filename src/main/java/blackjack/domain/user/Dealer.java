package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;

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
		return Collections.singletonList(getHand().get(0));
	}

	@Override
	public Boolean isWinner(Score dealerScore) {
		return !isBust();
	}

	@Override
	public boolean isLoser(Score dealerScore) {
		return isBust();
	}

	@Override
	public boolean canReceiveCard() {
		return computeScore().isUnder(MINIMUM_NUMBER_TO_DEALER_STAY);
	}
}
