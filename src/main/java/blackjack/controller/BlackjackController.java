package blackjack.controller;

import java.util.List;

import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

public class BlackjackController {
	private final Deck deck;
	private final Dealer dealer;
	private final List<Player> players;

	public BlackjackController(Deck deck, Dealer dealer, List<Player> players) {
		this.deck = deck;
		this.dealer = dealer;
		this.players = players;
	}

	public void playGame() {

	}
}
