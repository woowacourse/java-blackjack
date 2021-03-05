package blakcjack.controller;

import blakcjack.domain.card.Deck;
import blakcjack.domain.game.BlackjackGame;
import blakcjack.domain.outcome.Outcome;
import blakcjack.domain.outcome.OutcomeStatistics;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.participant.Player;
import blakcjack.domain.shufflestrategy.RandomShuffleStrategy;
import blakcjack.view.InputView;
import blakcjack.view.OutputView;

import java.util.List;
import java.util.Map;

import static blakcjack.view.InputView.YES;
import static blakcjack.view.InputView.takePlayerNamesInput;
import static blakcjack.view.OutputView.printInitialHands;

public class BlackJackController {
	public void run() {
		final List<String> playerNames = takePlayerNamesInput();
		Deck deck = new Deck(new RandomShuffleStrategy());
		final BlackjackGame blackjackGame = new BlackjackGame(deck, playerNames);
		blackjackGame.initializeHands();
		printInitialHands(blackjackGame);

		letPlayersDraw(blackjackGame);
		letDealerDraw(blackjackGame);

		OutcomeStatistics outcomeStatistics = getOutcomeStatistics(blackjackGame);
		OutputView.printFinalHandsSummary(blackjackGame);
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

	private void letDealerDraw(final BlackjackGame blackjackGame) {
		Dealer dealer = blackjackGame.getDealer();
		while (dealer.isScoreLowerThanMaximumDrawCriterion()) {
			blackjackGame.distributeOneCardTo(dealer);
			OutputView.printDealerAdditionalCardMessage();
		}
	}

	private OutcomeStatistics getOutcomeStatistics(final BlackjackGame blackjackGame) {
		Map<String, Outcome> playersOutcome = blackjackGame.getPlayersOutcome();
		return new OutcomeStatistics(playersOutcome);
	}
}
