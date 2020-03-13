package com.blackjack.controller;

import static com.blackjack.view.InputView.*;
import static com.blackjack.view.OutputView.*;

import java.util.List;

import com.blackjack.domain.GameRule;
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

		DrawController drawController = new DrawController();
		drawController.draw(cardDeck, dealer, players);

		printCards(dealer, players);

		printResult(dealer, players);
	}

	private void printResult(Dealer dealer, List<User> players) {
		GameRule gameRule = new GameRule(dealer, players);
		PlayerRecords playerRecords = gameRule.calculateResult();
		printResultMessage();
		printDealerRecord(playerRecords.calculateDealerResult());
		printUserRecords(playerRecords, players);
	}

	private List<User> createPlayers() {
		String names = inputPlayerNames();
		return PlayerFactory.createPlayers(names);
	}
}
