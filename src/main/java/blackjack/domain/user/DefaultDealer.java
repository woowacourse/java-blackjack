package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;

import java.util.Collections;
import java.util.List;

public class DefaultDealer extends AbstractPlayer {
	private DefaultDealer() {
		super(DEALER_NAME);
	}

	public static DefaultDealer dealer() {
		return new DefaultDealer();
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
	public boolean isDealer() {
		return true;
	}

	@Override
	public boolean canReceiveCard() {
		return getScore().isUnder(MINIMUM_NUMBER_TO_DEALER_STAY);
	}
}
