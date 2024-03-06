package blackjack.domain.gamer;

import java.util.ArrayList;

public class Player extends BlackJackGamer {

	public Player() {
		super(new ArrayList<>());
	}

	@Override
	public boolean canReceiveCard() {
		return sumCardNumbers() <= 21;
	}
}
