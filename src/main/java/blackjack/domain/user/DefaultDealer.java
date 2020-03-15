package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;

public class DefaultDealer extends AbstractPlayer implements Dealer {
	private DefaultDealer() {
		super(Dealer.NAME);
	}

	public static DefaultDealer dealer() {
		return new DefaultDealer();
	}

	@Override
	public boolean shouldReceiveCard() {
		return getScore().isUnder(MINIMUM_NUMBER_TO_STAY);
	}

	@Override
	public Card getFirstCard() {
		return getHand().get(0);
	}

	@Override
	public Boolean isWinner(Score dealerScore) {
		return isNotBust();
	}

	@Override
	public boolean isDealer() {
		return true;
	}
}
