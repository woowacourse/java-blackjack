package blackjack.domain.gamer;

import java.util.ArrayList;

public class Dealer extends BlackJackGamer {

	public Dealer() {
		super(new ArrayList<>());
	}

	@Override
	public boolean canReceiveCard() {
		return sumCardNumbers() <= 16;
	}
}
