package blackjack.domain;

import blackjack.service.BlackJackService;
import java.util.List;

public class Player extends Role {

	public Player(String name, Hand hand) {
		super(name, hand);
	}

	@Override
	public boolean canDraw() {
		final int score = hand.calculateOptimalScore();
		return BlackJackService.BUST < score && score <= BlackJackService.OPTIMIZED_WINNING_NUMBER;
	}

	@Override
	public List<Card> openHand() {
		return hand.getCards();
	}
}
