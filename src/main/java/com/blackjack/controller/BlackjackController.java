package com.blackjack.controller;

import static com.blackjack.domain.GameTable.FIRST_DRAW_COUNT;
import static com.blackjack.domain.user.Dealer.DRAW_CONDITION;
import static com.blackjack.view.InputView.inputBettingMoney;
import static com.blackjack.view.InputView.inputHitDecideType;
import static com.blackjack.view.InputView.inputPlayerNames;
import static com.blackjack.view.OutputView.printCardsAtFirst;
import static com.blackjack.view.OutputView.printDealerDrawMessage;
import static com.blackjack.view.OutputView.printDealerRecord;
import static com.blackjack.view.OutputView.printResultMessage;
import static com.blackjack.view.OutputView.printUserCardInfo;
import static com.blackjack.view.OutputView.printUserRecords;
import static com.blackjack.view.OutputView.printUserScore;

import java.util.ArrayList;
import java.util.List;

import com.blackjack.domain.GameRule;
import com.blackjack.domain.GameTable;
import com.blackjack.domain.HitDecideType;
import com.blackjack.domain.PlayerProfits;
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

	private List<Player> createPlayers() {
		List<String> playerNames = StringUtil.splitByDelimiter(inputPlayerNames());
		List<Integer> playerBettingMonies = new ArrayList<>();

		for (String playerName : playerNames) {
			playerBettingMonies.add(inputBettingMoney(playerName));
		}
		return PlayerFactory.createPlayers(playerNames, playerBettingMonies);
	}

	private void proceedGame(Dealer dealer, List<Player> players) {
		GameTable gameTable = new GameTable(dealer, players);
		gameTable.drawAtFirst();
		printCardsAtFirst(dealer, players, FIRST_DRAW_COUNT);

		drawAllPlayers(gameTable, players);
		drawDealerUntilEndTurn(gameTable, dealer);
		printUserCards(dealer, players);
	}

	private void printResult(Dealer dealer, List<Player> players) {
		GameRule gameRule = new GameRule(dealer, players);
		PlayerProfits playerProfits = gameRule.calculatePlayerProfits();
		printResultMessage();
		printDealerRecord(playerProfits.calculateDealerProfit());
		printUserRecords(playerProfits);
	}

	private void drawAllPlayers(GameTable gameTable, List<Player> players) {
		for (Player player : players) {
			drawPlayerUntilEndTurn(gameTable, player);
		}
	}

	private void drawDealerUntilEndTurn(GameTable gameTable, User dealer) {
		while (dealer.canDraw()) {
			gameTable.draw(dealer);
			printDealerDrawMessage(DRAW_CONDITION);
		}
	}

	private void printUserCards(Dealer dealer, List<Player> players) {
		System.out.println();
		printUserCardInfo(dealer);
		printUserScore(dealer.calculateScore());
		for (User player : players) {
			printUserCardInfo(player);
			printUserScore(player.calculateScore());
		}
	}

	private void drawPlayerUntilEndTurn(GameTable gameTable, Player player) {
		while (player.canDraw() && isHit(player)) {
			gameTable.draw(player);
			printUserCardInfo(player);
		}
	}

	private boolean isHit(Player player) {
		HitDecideType hitOrNot = HitDecideType.of(inputHitDecideType(player));
		return hitOrNot.isHit();
	}
}