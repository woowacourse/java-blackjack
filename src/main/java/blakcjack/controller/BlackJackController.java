package blakcjack.controller;

import blakcjack.domain.card.Deck;
import blakcjack.domain.game.BlackjackGame;
import blakcjack.domain.outcome.OutcomeStatistics;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Player;
import blakcjack.domain.shufflestrategy.RandomShuffleStrategy;
import blakcjack.view.InputView;
import blakcjack.view.OutputView;

import java.util.List;

import static blakcjack.view.InputView.takePlayerNamesInput;
import static blakcjack.view.OutputView.printInitialHands;

public class BlackJackController {
	public void run() {
		final List<String> playerNames = takePlayerNamesInput();
		final Deck deck = new Deck(new RandomShuffleStrategy());
		final BlackjackGame blackjackGame = new BlackjackGame(deck, playerNames);
		blackjackGame.initializeHands();
		printInitialHands(blackjackGame);

		letPlayersDraw(blackjackGame);
		letDealerDraw(blackjackGame);

		OutcomeStatistics outcomeStatistics = blackjackGame.getOutcomeStatistics();
		OutputView.printFinalHandsSummary(blackjackGame);
		OutputView.printFinalOutcomeSummary(outcomeStatistics);
	}

	private void letPlayersDraw(final BlackjackGame blackjackGame) {
		final List<Player> players = blackjackGame.getPlayers();
		for (final Player player : players) {
			decideToDraw(blackjackGame, player);
		}
	}

	private void decideToDraw(final BlackjackGame blackjackGame, final Player player) {
		while (isHit(player)) {
			blackjackGame.distributeOneCardTo(player);
			OutputView.printPlayerHand(player);
		}
	}

	private boolean isHit(final Player player) {
		return !player.isBust() && InputView.isYes(player);
	}

	private void letDealerDraw(final BlackjackGame blackjackGame) {
		final Dealer dealer = blackjackGame.getDealer();
		while (dealer.isScoreLowerThanMaximumDrawCriterion()) {
			blackjackGame.distributeOneCardTo(dealer);
			OutputView.printDealerAdditionalCardMessage();
		}
	}
}
