package blackjack.domain.role;

import blackjack.domain.BettingAmount;
import blackjack.domain.BlackJack;
import blackjack.domain.card.Hand;

public class Player extends Role {

	public Player(final String name, final Hand hand, final BettingAmount bettingAmount) {
		super(name, hand, bettingAmount);
	}

	@Override
	public boolean canDraw() {
		final int score = hand.calculateOptimalScore();
		if (hand.isBlackJack()) {
			return false;
		}
		return BlackJack.BUST < score && score <= BlackJack.OPTIMIZED_WINNING_NUMBER;
	}

	@Override
	public int getDrawStandard() {
		return BlackJack.OPTIMIZED_WINNING_NUMBER - 1;
	}
}
