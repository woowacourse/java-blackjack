package blackjack;

import blackjack.domain.Deck;

public class BlackJackApplication {

	public static void main(String[] args) {
		Deck deck = Deck.initializeDeck();
		BlackJackGame blackJackGame = new BlackJackGame(deck);
		blackJackGame.run();
	}

}
