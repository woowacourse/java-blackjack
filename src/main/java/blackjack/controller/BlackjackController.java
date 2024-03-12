package blackjack.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.result.GameResult;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {
	private final InputView inputView;
	private final OutputView outputView;

	public BlackjackController(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void startGame() {
		BlackjackGame blackjackGame = BlackjackGame.getInstance();
		Dealer dealer = blackjackGame.createDealer();
		Players players = blackjackGame.createPlayers(inputView.readPlayerNames());

		dealInitCards(dealer, players, blackjackGame);
		receiveAdditionalCard(dealer, players);
		printResult(dealer, players, blackjackGame);
	}

	private void dealInitCards(Dealer dealer, Players players, BlackjackGame blackjackGame) {
		blackjackGame.dealInitCards(dealer, players);
		outputView.printInitCardStatus(dealer, players);
	}

	private void receiveAdditionalCard(Dealer dealer, Players players) {
		for (Player player : players.getPlayers()) {
			receivePlayerAdditionalCard(dealer, player);
		}
		receiveDealerAdditionalCard(dealer);
	}

	private void receivePlayerAdditionalCard(Dealer dealer, Player player) {
		while (!player.isBust() && isPlayerInputHit(player)) {
			player.receiveCard(dealer.dealCard());
			outputView.printCardHandStatus(player);
		}
	}

	private boolean isPlayerInputHit(Player player) {
		return inputView.readHitOrStand(player);
	}

	private void receiveDealerAdditionalCard(Dealer dealer) {
		while (dealer.tryHit()) {
			outputView.printDealerHitMessage();
		}
	}

	public void printResult(Dealer dealer, Players players, BlackjackGame blackjackGame) {
		outputView.printTotalCardHandStatus(dealer, players);
		printGameResult(dealer, players, blackjackGame);
	}

	private void printGameResult(Dealer dealer, Players players, BlackjackGame blackjackGame) {
		Map<Player, GameResult> playerResults = blackjackGame.getPlayerResults(players, dealer);
		Map<GameResult, Long> dealerResult = blackjackGame.getDealerResult(playerResults);
		outputView.printGameResult(dealerResult, playerResults);
	}
}
