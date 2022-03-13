package blackjack.domain;

import java.util.List;

public class Player extends Role {

	public Player(String name, Hand hand) {
		super(name, hand);
	}

	@Override
	public boolean canDraw() {
		final int score = hand.calculateOptimalScore();
		return Hand.BUST < score && score <= Hand.OPTIMIZED_WINNING_NUMBER;
	}

	@Override
	public List<Card> openHand() {
		return hand.getCards();
	}
}
