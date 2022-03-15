package blackjack;

import blackjack.service.BlackJack;

public class BlackJackApplication {

	public static void main(String[] args) {
		final BlackJack blackJack = new BlackJack();
		blackJack.startGame();
	}

}
