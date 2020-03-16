package controller;

import java.util.List;

import domain.card.CardDeck;
import domain.result.DealerResult;
import domain.result.PlayerResults;
import domain.result.ScoreBoards;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayerFactory;
import util.PlayerDrawResponse;
import view.InputView;
import view.OutputView;

public class BlackjackGame {
	public void play() {
		final List<Player> players = PlayerFactory.create(InputView.inputNames());
		final Dealer dealer = new Dealer();
		final CardDeck cardDeck = new CardDeck();
		drawInitialUsersCard(players, dealer, cardDeck);
		drawAdditionalUsersCard(players, dealer, cardDeck);
		printAllUserScore(players, dealer);
	}

	private void drawInitialUsersCard(List<Player> players, Dealer dealer, CardDeck cardDeck) {
		drawInitialPlayersCard(players, cardDeck);
		drawInitialDealerCard(dealer, cardDeck);
		OutputView.printInitialResult(players, dealer);
	}

	private void drawInitialPlayersCard(List<Player> players, CardDeck cardDeck) {
		for (Player player : players) {
			player.drawFirst(cardDeck);
		}
	}

	private void drawInitialDealerCard(Dealer dealer, CardDeck cardDeck) {
		dealer.drawFirst(cardDeck);
	}

	private void drawAdditionalUsersCard(List<Player> players, Dealer dealer, CardDeck cardDeck) {
		if (dealer.isBlackjack()) {
			return;
		}
		drawAdditionalPlayersCard(players, cardDeck);
		drawAdditionalDealerCard(dealer, cardDeck);
	}

	private void drawAdditionalPlayersCard(List<Player> players, CardDeck cardDeck) {
		for (Player player : players) {
			drawAdditionalPlayerCard(player, cardDeck);
		}
	}

	private void drawAdditionalPlayerCard(Player player, CardDeck cardDeck) {
		while (player.isDrawable() && isYes(player)) {
			player.draw(cardDeck);
			OutputView.printPlayerCard(player);
		}
	}

	private boolean isYes(Player player) {
		return PlayerDrawResponse.isYes(InputView.inputYesOrNo(player.getName()));
	}

	private void drawAdditionalDealerCard(Dealer dealer, CardDeck cardDeck) {
		if (dealer.isDrawable()) {
			dealer.draw(cardDeck);
			OutputView.printDealerDraw();
		}
	}

	private void printAllUserScore(List<Player> players, Dealer dealer) {
		ScoreBoards scoreBoards = ScoreBoards.fromAllUsers(players, dealer);
		OutputView.printUsersCardsAndScore(scoreBoards);
		DealerResult dealerResult = scoreBoards.calculateDealerResults();
		PlayerResults playerResults = scoreBoards.calculatePlayersResult();
		OutputView.printGameResult(playerResults, dealerResult);
	}
}
