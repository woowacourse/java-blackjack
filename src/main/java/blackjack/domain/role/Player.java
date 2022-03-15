package blackjack.domain.role;

import blackjack.domain.BlackJack;
import blackjack.domain.card.Hand;

public class Player extends Role {

	public Player(String name, Hand hand) {
		super(name, hand);
	}

	@Override
	public boolean canDraw() {
		final int score = hand.calculateOptimalScore();
		return BlackJack.BUST < score && score <= BlackJack.OPTIMIZED_WINNING_NUMBER;
	}

	@Override
	public int getDrawStandard() {
		return BlackJack.OPTIMIZED_WINNING_NUMBER - 1;
	}
}
