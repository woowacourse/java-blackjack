package blackjack.domain.user;

import java.util.List;

import blackjack.domain.card.Card;

public class Player extends User {
	private static final int PLAYER_DRAWABLE_MAX_SCORE = 22;

	public Player(String name) {
		super(name);
	}

	@Override
	public boolean canDraw() {
		return hand.calculateScore().isLowerThan(PLAYER_DRAWABLE_MAX_SCORE);
	}

	@Override
	public List<Card> getInitialHand() {
		return super.getHand();
	}
}
