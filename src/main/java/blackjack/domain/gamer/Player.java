package blackjack.domain.gamer;

import java.util.List;

import blackjack.domain.BlackjackConstants;
import blackjack.domain.card.Card;

public class Player extends BlackjackGamer {

	public Player(Name name) {
		super(name);
	}

	public Player(Name name, List<Card> cards) {
		super(name, cards);
	}

	@Override
	public boolean canReceiveCard() {
		return getScore() <= BlackjackConstants.BLACKJACK_VALUE.getValue();
	}
}
