package com.blackjack.controller;

import static com.blackjack.view.InputView.*;
import static com.blackjack.view.OutputView.*;

import java.util.List;

import com.blackjack.domain.DrawDecideType;
import com.blackjack.domain.card.CardDeck;
import com.blackjack.domain.user.User;

class DrawController {
	void draw(CardDeck cardDeck, User dealer, List<User> players) {
		drawAtFirst(cardDeck, dealer, players);
		printCardsAtFirst(dealer, players);

		drawAllPlayers(cardDeck, players);
		drawDealerUntilEndTurn(cardDeck, dealer);
	}

	private void drawDealerUntilEndTurn(CardDeck cardDeck, User dealer) {
		while (dealer.canDraw()) {
			dealer.draw(cardDeck);
			printDealerDrawMessage();
		}
	}

	private void drawAllPlayers(CardDeck cardDeck, List<User> players) {
		for (User player : players) {
			drawPlayerUntilEndTurn(cardDeck, player);
		}
	}

	private void drawPlayerUntilEndTurn(CardDeck cardDeck, User player) {
		while (canDraw(player)) {
			player.draw(cardDeck);
			printUserCardInfo(player);
			System.out.println();
		}
	}

	private boolean canDraw(User player) {
		return player.canDraw() && DrawDecideType.isDraw(inputDrawDecideType(player));
	}

	private void drawAtFirst(CardDeck cardDeck, User dealer, List<User> players) {
		dealer.drawAtFirst(cardDeck);
		for (User player : players) {
			player.drawAtFirst(cardDeck);
		}
	}
}
