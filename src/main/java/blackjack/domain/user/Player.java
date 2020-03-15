package blackjack.domain.user;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.user.hand.Score;

public class Player extends User {
	private static final int PLAYER_DRAWABLE_MAX_SCORE = 21;

	public Player(String name) {
		super(name);
	}

	Player(String name, List<Card> cards) {
		super(name, cards);
	}

	public static Player valueOf(String name, List<Card> cards) {
		return new Player(name, cards);
	}

	@Override
	public boolean canDraw() {
		// NOTE : 의인화?!
		return hand.calculateScore().getScore() > Score.BUST.getScore();
	}

	@Override
	public List<Card> getInitialHand() {
		return super.getHand();
	}
}
