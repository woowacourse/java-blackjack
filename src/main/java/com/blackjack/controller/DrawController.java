package com.blackjack.controller;

import static com.blackjack.view.InputView.*;
import static com.blackjack.view.OutputView.*;

import java.util.List;

import com.blackjack.domain.DrawDecideType;
import com.blackjack.domain.card.CardDeck;
import com.blackjack.domain.user.Drawable;

class DrawController {
	void draw(CardDeck cardDeck, Drawable dealer, List<Drawable> players) {
		drawAtFirst(cardDeck, dealer, players);
		printCardsAtFirst(dealer, players);

		drawAllPlayers(cardDeck, players);
		drawDealerUntilEndTurn(cardDeck, dealer);
	}

	private void drawDealerUntilEndTurn(CardDeck cardDeck, Drawable dealer) {
		while (dealer.canDraw()) {
			dealer.draw(cardDeck);
			printDealerDrawMessage();
		}
	}

	private void drawAllPlayers(CardDeck cardDeck, List<Drawable> players) {
		for (Drawable player : players) {
			drawPlayerUntilEndTurn(cardDeck, player);
		}
	}

	private void drawPlayerUntilEndTurn(CardDeck cardDeck, Drawable player) {
		while (canDraw(player)) {
			player.draw(cardDeck);
			printUserCardInfo(player);
		}
	}

	private boolean canDraw(Drawable player) {
		return player.canDraw() && DrawDecideType.DRAW.equals(createDrawDecideType(player));
	}

	private DrawDecideType createDrawDecideType(Drawable player) {
		return DrawDecideType.of(inputDrawDecideType(player));
	}

	private void drawAtFirst(CardDeck cardDeck, Drawable dealer, List<Drawable> players) {
		dealer.drawAtFirst(cardDeck);
		for (Drawable player : players) {
			player.drawAtFirst(cardDeck);
		}
	}

}
