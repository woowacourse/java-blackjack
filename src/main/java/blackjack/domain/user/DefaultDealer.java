package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;

public class DefaultDealer extends AbstractPlayer implements Dealer {
	private DefaultDealer() {
		super(NAME);
	}

	public static DefaultDealer create() {
		return new DefaultDealer();
	}

	@Override
    public boolean shouldReceiveCard() {
		return calculateScore().isUnder(MINIMUM_NUMBER_TO_STAY);
	}

	@Override
	public Card showFirstCard() {
		return getCards().get(0);
	}

	@Override
	public Boolean isWinner(Score dealerScore) {
		return isNotBust();
	}
}
