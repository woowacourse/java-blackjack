package controller;

import java.util.ArrayList;
import java.util.List;

import domain.card.CardDeck;
import domain.result.DealerResult;
import domain.result.PlayerResults;
import domain.result.ResultCalculator;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayerFactory;
import domain.user.User;
import util.YesOrNo;
import view.InputView;
import view.OutputView;

public class BlackjackGame {
	private final CardDeck cardDeck;

	public BlackjackGame(CardDeck cardDeck) {
		this.cardDeck = cardDeck;
	}

	public void play() {
		final List<Player> players = PlayerFactory.create(InputView.inputNames());
		final Dealer dealer = new Dealer();
		drawInitialUsersCard(players, dealer);
		drawAdditionalUsersCard(players, dealer);
		printAllUserScore(players, dealer);
		printGameResult(players, dealer);
	}

	private void drawInitialUsersCard(List<Player> players, Dealer dealer) {
		drawInitialPlayersCard(players);
		drawInitialDealerCard(dealer);
		OutputView.printInitialResult(players, dealer);
	}

	private void drawInitialPlayersCard(List<Player> players) {
		for (Player player : players) {
			player.addCards(cardDeck.draw(), cardDeck.draw());
		}
	}

	private void drawInitialDealerCard(Dealer dealer) {
		dealer.addCards(cardDeck.draw(), cardDeck.draw());
	}

	private void drawAdditionalUsersCard(List<Player> players, Dealer dealer) {
		if (dealer.isBlackjack()) {
			return;
		}
		drawAdditionalPlayersCard(players);
		drawAdditionalDealerCard(dealer);
	}

	private void drawAdditionalPlayersCard(List<Player> players) {
		for (Player player : players) {
			drawAdditionalPlayerCard(player);
		}
	}

	private void drawAdditionalPlayerCard(Player player) {
		while (player.isDrawable() && isYes(player)) {
			player.addCards(cardDeck.draw());
			OutputView.printPlayerCard(player);
		}
	}

	private boolean isYes(Player player) {
		YesOrNo yesOrNo = YesOrNo.of(InputView.inputYesOrNo(player.getName()));
		return yesOrNo.isYes();
	}

	private void drawAdditionalDealerCard(Dealer dealer) {
		if (dealer.isDrawable()) {
			dealer.addCards(cardDeck.draw());
			OutputView.printDealerDraw();
		}
	}

	private void printAllUserScore(List<Player> players, Dealer dealer) {
		List<User> allUsers = new ArrayList<>(players);
		allUsers.add(dealer);
		OutputView.printUserResult(allUsers);
	}

	private void printGameResult(List<Player> players, Dealer dealer) {
		ResultCalculator calculator = new ResultCalculator(players, dealer);
		PlayerResults playersResult = calculator.calculatePlayersResult();
		DealerResult dealerResult = calculator.calculateDealerResults();
		OutputView.printGameResult(playersResult, dealerResult);
	}
}
