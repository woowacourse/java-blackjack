package com.blackjack.domain;

import java.util.List;

import com.blackjack.domain.card.Card;
import com.blackjack.domain.card.CardDeck;
import com.blackjack.domain.user.Dealer;
import com.blackjack.domain.user.User;

public class GameTable {
	public static final int FIRST_DRAW_COUNT = 2;

	private final Dealer dealer;
	private final List<User> players;
	private final CardDeck cardDeck;

	public GameTable(Dealer dealer, List<User> players) {
		this.dealer = dealer;
		this.players = players;
		this.cardDeck = CardDeck.create();
	}

	public void drawAtFirst() {
		for (int count = 0; count < FIRST_DRAW_COUNT; count++) {
			drawToDealer();
			drawToPlayers();
		}
	}

	public void draw(User user) {
		Card card = cardDeck.pop();
		user.draw(card);
	}

	private void drawToPlayers() {
		for (User player : players) {
			draw(player);
		}
	}

	private void drawToDealer() {
		draw(dealer);
	}
}