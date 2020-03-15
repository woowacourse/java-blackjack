package com.blackjack.controller;

import static com.blackjack.view.InputView.inputPlayerNames;
import static com.blackjack.view.OutputView.printDealerRecord;
import static com.blackjack.view.OutputView.printResultMessage;
import static com.blackjack.view.OutputView.printUserRecords;

import java.util.List;

import com.blackjack.domain.GameRule;
import com.blackjack.domain.PlayerRecords;
import com.blackjack.domain.user.Dealer;
import com.blackjack.domain.user.PlayerFactory;
import com.blackjack.domain.user.User;

public class BlackjackController {
	public void run() {
		Dealer dealer = new Dealer();
		List<User> players = createPlayers();

		draw(dealer, players);

		printResult(dealer, players);
	}

	private void draw(Dealer dealer, List<User> players) {
		DrawProceedController drawProceedController = new DrawProceedController();
		drawProceedController.run(dealer, players);
	}

	private List<User> createPlayers() {
		String names = inputPlayerNames();
		return PlayerFactory.createPlayers(names);
	}

	private void printResult(Dealer dealer, List<User> players) {
		GameRule gameRule = new GameRule(dealer, players);

		PlayerRecords playerRecords = gameRule.calculateResult();
		printResultMessage();

		printDealerRecord(playerRecords.calculateDealerResult());
		printUserRecords(playerRecords, players);
	}
}
