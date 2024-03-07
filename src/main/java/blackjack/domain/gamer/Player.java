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

	public boolean isWin(int dealerScore) {
		int playerScore = sumCardNumbers();

		if (playerScore > 21) {
			return false;
		}
		if (dealerScore > 21) {
			return true;
		}

		return playerScore > dealerScore;
	}
}
