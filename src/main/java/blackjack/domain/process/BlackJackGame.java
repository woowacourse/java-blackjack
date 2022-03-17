package blackjack.domain.process;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Gamer;

public class BlackJackGame {
	private static final int INIT_DISTRIBUTE_AMOUNT = 2;
	private static final int EACH_TURN_DISTRIBUTE_AMOUNT = 1;
	private final Deck deck;

	public BlackJackGame(Deck deck) {
		this.deck = deck;
	}

	public void drawTo(Gamer gamer) {
		gamer.addCards(this.deck.distributeCards(EACH_TURN_DISTRIBUTE_AMOUNT));
	}

	public void initDrawTo(Gamer gamer) {
		gamer.addCards(this.deck.distributeCards(INIT_DISTRIBUTE_AMOUNT));
	}

	public void shuffleDeck() {
		this.deck.shuffle();
	}
}
