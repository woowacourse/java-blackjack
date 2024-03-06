package blackjack.domain.gamer;

import java.util.ArrayList;

public class Dealer extends BlackJackGamer {

	public Dealer() {
		super(new Name("딜러"), new ArrayList<>());
	}

	@Override
	public boolean canReceiveCard() {
		return sumCardNumbers() <= 16;
	}
}
