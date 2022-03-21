package blackjack.domain.process;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

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

	private void initDrawTo(Gamer gamer) {
		gamer.addCards(this.deck.distributeCards(INIT_DISTRIBUTE_AMOUNT));
	}

	private void shuffleDeck() {
		this.deck.shuffle();
	}

	public void deal(Players players, Dealer dealer) {
		shuffleDeck();
		initDrawTo(dealer);
		for (Player player : players.getPlayers()) {
			initDrawTo(player);
		}
	}

}
