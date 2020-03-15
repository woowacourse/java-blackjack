package com.blackjack.controller;

import static com.blackjack.view.InputView.inputDrawDecideType;
import static com.blackjack.view.OutputView.printCards;
import static com.blackjack.view.OutputView.printCardsAtFirst;
import static com.blackjack.view.OutputView.printDealerDrawMessage;
import static com.blackjack.view.OutputView.printUserCardInfo;

import java.util.List;

import com.blackjack.domain.DrawDecideType;
import com.blackjack.domain.card.CardDeck;
import com.blackjack.domain.user.Dealer;
import com.blackjack.domain.user.User;

/**
 * GameProceedController class
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/03/15
 */
public class DrawProceedController {
	private final CardDeck cardDeck;

	public DrawProceedController() {
		this.cardDeck = CardDeck.create();
	}

	public void run(Dealer dealer, List<User> players) {
		drawAtFirst(dealer, players);
		printCardsAtFirst(dealer, players);

		drawUntilEndTurn(dealer, players);
		printCards(dealer, players);
	}

	private void drawAtFirst(Dealer dealer, List<User> players) {
		dealer.drawAtFirst(cardDeck);
		for (User player : players) {
			player.drawAtFirst(cardDeck);
		}
	}

	private void drawUntilEndTurn(Dealer dealer, List<User> players) {
		drawPlayersUntilEndTurn(players);
		drawDealerUntilEndTurn(dealer);
	}

	private void drawPlayersUntilEndTurn(List<User> players) {
		for (User player : players) {
			drawPlayerUntilEndTurn(player);
		}
	}

	private void drawPlayerUntilEndTurn(User player) {
		while (player.canDraw() && isUserDecideDraw(player)) {
			player.draw(cardDeck);
			printUserCardInfo(player);
		}
	}

	private boolean isUserDecideDraw(User player) {
		return DrawDecideType.DRAW.equals(createDrawDecideType(player));
	}

	private DrawDecideType createDrawDecideType(User player) {
		return DrawDecideType.of(inputDrawDecideType(player));
	}

	private void drawDealerUntilEndTurn(Dealer dealer) {
		while (dealer.canDraw()) {
			dealer.draw(cardDeck);
			printDealerDrawMessage();
		}
	}
}
