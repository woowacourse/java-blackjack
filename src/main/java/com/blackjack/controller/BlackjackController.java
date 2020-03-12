package com.blackjack.controller;

import static com.blackjack.view.InputView.*;
import static com.blackjack.view.OutputView.*;

import java.util.List;

import com.blackjack.domain.DrawDecideType;
import com.blackjack.domain.GameTable;
import com.blackjack.domain.PlayerRecords;
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

		drawAllPlayers(cardDeck, players);
		drawDealerEndTurn(cardDeck, dealer);

		printCards(dealer, players);

		printResult(dealer, players);
	}

	private void printResult(Dealer dealer, List<User> players) {
		GameTable gameTable = new GameTable(dealer, players);
		PlayerRecords playerRecords = gameTable.calculateResult();
		printResultMessage();
		printDealerRecord(playerRecords.calculateDealerResult());
		printUserRecords(playerRecords, players);
	}

	private void drawDealerEndTurn(CardDeck cardDeck, Dealer dealer) {
		while (dealer.canDraw()) {
			dealer.draw(cardDeck);
			printDealerDrawMessage();
		}
	}

	private void drawAllPlayers(CardDeck cardDeck, List<User> players) {
		for (User player : players) {
			drawUntilEndTurn(cardDeck, player);
		}
	}

	private void drawUntilEndTurn(CardDeck cardDeck, User player) {
		while (canDraw(player)) {
			player.draw(cardDeck);
			printUserCardInfo(player);
		}
	}

	private boolean canDraw(User player) {
		return player.canDraw() && DrawDecideType.DRAW.equals(createDrawDecideType(player));
	}

	private DrawDecideType createDrawDecideType(User player) {
		return DrawDecideType.of(inputDrawDecideType(player));
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
