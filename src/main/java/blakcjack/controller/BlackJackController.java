package blakcjack.controller;

import blakcjack.domain.card.Deck;
import blakcjack.domain.game.BlackjackGame;
import blakcjack.domain.name.Names;
import blakcjack.domain.outcome.OutcomeStatistics;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Player;
import blakcjack.domain.shufflestrategy.RandomShuffleStrategy;
import blakcjack.view.OutputView;

import java.util.List;

import static blakcjack.view.InputView.isYes;
import static blakcjack.view.InputView.takePlayerNamesInput;
import static blakcjack.view.OutputView.*;

public class BlackJackController {
	public void run() {
		final BlackjackGame blackjackGame = createBlackjackGame();
		blackjackGame.initializeHands();
		printInitialHands(blackjackGame);

		play(blackjackGame);

		OutcomeStatistics outcomeStatistics = blackjackGame.getOutcomeStatistics();
		OutputView.printFinalHandsSummary(blackjackGame);
		OutputView.printFinalOutcomeSummary(outcomeStatistics);
	}

	private BlackjackGame createBlackjackGame() {
		final Names playerNames = new Names(takePlayerNamesInput());
		final Deck deck = new Deck(new RandomShuffleStrategy());
		return new BlackjackGame(deck, playerNames);
	}

	private void play(final BlackjackGame blackjackGame) {
		Deck deck = blackjackGame.getDeck();
		List<Player> players = blackjackGame.getPlayers();
		Dealer dealer = blackjackGame.getDealer();
		letPlayersDraw(players, deck);
		letDealerDraw(dealer, deck);
	}

	private void letPlayersDraw(final List<Player> players, final Deck deck) {
		for (final Player player : players) {
			letPlayerDraw(player, deck);
		}
	}

	private void letPlayerDraw(final Player player, final Deck deck) {
		while (isHit(player)) {
			player.drawOneCardFrom(deck);
			printPlayerHand(player);
		}
	}

	private boolean isHit(final Player player) {
		return !player.isBust() && isYes(player);
	}

	private void letDealerDraw(final Dealer dealer, final Deck deck) {
		while (dealer.isScoreLowerThanMaximumDrawCriterion()) {
			dealer.drawOneCardFrom(deck);
			printDealerAdditionalCardMessage();
		}
	}
}
