package blackjack.domain.gamer;

import java.util.ArrayList;

public class Player extends BlackJackGamer {

	public Player(Name name) {
		super(name, new ArrayList<>());
	}

	@Override
	public boolean canReceiveCard() {
		return sumCardNumbers() <= 21;
	}
}
