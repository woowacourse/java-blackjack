package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;

public class DefaultDealer extends AbstractPlayer implements Dealer {
	private static final int MINIMUM_NUMBER_TO_STAY = 17;

	private DefaultDealer() {
		super(NAME);
	}

	public static DefaultDealer dealer() {
		return new DefaultDealer();
	}

	@Override
    public boolean shouldReceiveCard() {
		return calculateScore().isUnder(MINIMUM_NUMBER_TO_STAY);
	}

	@Override
	public Card getFirstCard() {
		return getCards().get(0);
	}

	@Override
	public Boolean isWinner(Score dealerScore) {
		return isNotBust();
	}

	@Override
	public int getMinimumNumberToStay() {
		return MINIMUM_NUMBER_TO_STAY;
	}

	@Override
	public boolean isDealer() {
		return true;
	}
}
