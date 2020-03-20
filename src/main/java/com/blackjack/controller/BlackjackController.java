package com.blackjack.controller;

import static com.blackjack.domain.GameTable.*;
import static com.blackjack.domain.user.Dealer.*;
import static com.blackjack.view.InputView.*;
import static com.blackjack.view.OutputView.*;

import java.util.ArrayList;
import java.util.List;

import com.blackjack.domain.DrawDecideType;
import com.blackjack.domain.GameRule;
import com.blackjack.domain.GameTable;
import com.blackjack.domain.PlayerRecords;
import com.blackjack.domain.user.Dealer;
import com.blackjack.domain.user.Player;
import com.blackjack.domain.user.PlayerFactory;
import com.blackjack.domain.user.User;
import com.blackjack.util.StringUtil;

public class BlackjackController {
	public void run() {
		Dealer dealer = new Dealer();
		List<Player> players = createPlayers();

		proceedGame(dealer, players);
		printResult(dealer, players);
	}

	private void proceedGame(Dealer dealer, List<Player> players) {
		GameTable gameTable = new GameTable(dealer, players);
		gameTable.drawAtFirst();
		printCardsAtFirst(dealer, players, FIRST_DRAW_COUNT);

		drawAllPlayers(gameTable, players);
		drawDealerUntilEndTurn(gameTable, dealer);

		printUserCards(dealer, players);
	}

	private void printUserCards(Dealer dealer, List<Player> players) {
		System.out.println();
		printUserCardInfo(dealer);
		printUserScore(dealer.calculateHand());
		for (User player : players) {
			printUserCardInfo(player);
			printUserScore(player.calculateHand());
		}
	}

	private void printResult(Dealer dealer, List<Player> players) {
		GameRule gameRule = new GameRule(dealer, players);

		PlayerRecords playerRecords = gameRule.calculateResult();
		printResultMessage();

		printDealerRecord(playerRecords.calculateDealerResult());
		printUserRecords(playerRecords);
	}

	private List<Player> createPlayers() {
		List<String> playerNames = StringUtil.splitByDelimiter(inputPlayerNames());
		List<Integer> playerBettingMonies = new ArrayList<>();

		for (String playerName : playerNames) {
			playerBettingMonies.add(inputBettingMoney(playerName));
		}
		return PlayerFactory.createPlayers(playerNames, playerBettingMonies);
	}

	private void drawDealerUntilEndTurn(GameTable gameTable, User dealer) {
		while (dealer.canDraw()) {
			gameTable.draw(dealer);
			printDealerDrawMessage(DRAW_CONDITION);
		}
	}

	private void drawAllPlayers(GameTable gameTable, List<Player> players) {
		for (Player player : players) {
			drawPlayerUntilEndTurn(gameTable, player);
		}
	}

	private void drawPlayerUntilEndTurn(GameTable gameTable, Player player) {
		while (canDraw(player) && wantDraw(player)) {
			gameTable.draw(player);
			printUserCardInfo(player);
			System.out.println();
		}
	}

	private boolean canDraw(Player player) {
		return player.canDraw();
	}

	private boolean wantDraw(Player player) {
		return DrawDecideType.isDraw(inputDrawDecideType(player));
	}
}