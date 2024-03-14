package blackjack.domain.gamer;

import java.util.List;

import blackjack.domain.BlackjackConstants;
import blackjack.domain.card.Card;

public class Player extends BlackjackGamer {

	public Player(String name) {
		super(name);
	}

	public Player(String name, List<Card> cards) {
		super(name, cards);
	}

	@Override
	public boolean canReceiveCard() {
		return getScore() <= BlackjackConstants.BLACKJACK_VALUE.getValue();
	}
}
