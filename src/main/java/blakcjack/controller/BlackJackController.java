package blakcjack.controller;

import blakcjack.View.InputView;
import blakcjack.View.OutputView;
import blakcjack.domain.BlackjackGame;
import blakcjack.domain.OutcomeStatistics;
import blakcjack.domain.card.Deck;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.participant.Player;

import java.util.List;

import static blakcjack.View.InputView.takePlayerNamesInput;
import static blakcjack.View.OutputView.printInitialHands;

public class BlackJackController {
	private static final String YES = "y";

	public void run() {
		final List<String> playerNames = takePlayerNamesInput();
		final BlackjackGame blackjackGame = new BlackjackGame(new Deck(), playerNames);
		blackjackGame.initializeHands();
		printInitialHands(blackjackGame);

		letPlayersDraw(blackjackGame);
		drawDealerCardForMaximumCapability(blackjackGame);

		OutputView.printFinalHandsSummary(blackjackGame);
		OutcomeStatistics outcomeStatistics = new OutcomeStatistics(blackjackGame);
		OutputView.printFinalOutcomeSummary(outcomeStatistics);
	}

	private void letPlayersDraw(final BlackjackGame blackjackGame) {
		final List<Player> players = blackjackGame.getPlayers();
		for (final Participant player : players) {
			decideToDraw(blackjackGame, player);
		}
	}

	private void decideToDraw(final BlackjackGame blackjackGame, final Participant player) {
		while (player.isScoreLowerThanBlackJackValue() && isYes(player)) {
			blackjackGame.distributeOneCardTo(player);
			OutputView.printPlayerHand(player);
		}
	}

	private boolean isYes(final Participant player) {
		final String yesOrNo = InputView.takeHitOrStand(player.getName());
		return YES.equals(yesOrNo);
	}

	private void drawDealerCardForMaximumCapability(final BlackjackGame blackjackGame) {
		Dealer dealer = blackjackGame.getDealer();
		while (dealer.isScoreLowerThanMaximumDrawCriterion()) {
			blackjackGame.distributeOneCardTo(dealer);
			OutputView.printDealerAdditionalCardMessage();
		}
	}
}
