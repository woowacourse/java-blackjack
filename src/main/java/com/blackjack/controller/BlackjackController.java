package com.blackjack.controller;

import static com.blackjack.view.InputView.*;
import static com.blackjack.view.OutputView.*;

import java.util.List;

import com.blackjack.domain.card.CardDeck;
import com.blackjack.domain.user.Dealer;
import com.blackjack.domain.user.PlayerFactory;
import com.blackjack.domain.user.User;

public class BlackjackController {
	public void run() {
		CardDeck cardDeck = CardDeck.create();
		Dealer dealer = new Dealer();
		List<User> players = createPlayers();

		drawAtFirst(cardDeck, dealer, players);
		printCardsAtFirst(dealer, players);
	}

	private void drawAtFirst(CardDeck cardDeck, User dealer, List<User> players) {
		dealer.drawAtFirst(cardDeck);
		for (User player : players) {
			player.drawAtFirst(cardDeck);
		}
	}

	private List<User> createPlayers() {
		String names = inputPlayerNames();
		return PlayerFactory.createPlayers(names);
	}
}
