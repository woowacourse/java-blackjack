package blakcjack.controller;

import blakcjack.domain.card.Deck;
import blakcjack.domain.name.Names;
import blakcjack.domain.outcome.OutcomeStatistics;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Player;
import blakcjack.domain.participant.Players;
import blakcjack.domain.shufflestrategy.RandomShuffleStrategy;

import static blakcjack.view.InputView.isYes;
import static blakcjack.view.InputView.takePlayerNamesInput;
import static blakcjack.view.OutputView.*;

public class BlackJackController {
	public void run() {
		final Players players = createPlayers();
		final Dealer dealer = new Dealer();

		playGame(players, dealer);

		OutcomeStatistics outcomeStatistics = players.getOutcomeStatisticsBy(dealer);
		printFinalHandsSummaryOf(players, dealer);
		printFinalOutcomeSummary(outcomeStatistics);
	}

	private Players createPlayers() {
		final Names playerNames = new Names(takePlayerNamesInput());
		return new Players(playerNames);
	}

	private void playGame(final Players players, final Dealer dealer) {
		final Deck deck = new Deck(new RandomShuffleStrategy());
		players.initializeHandsFrom(deck);
		dealer.initializeHandFrom(deck);
		printInitialHandsOf(players, dealer);

		letPlayersDraw(players, deck);
		letDealerDraw(dealer, deck);
	}

	private void letPlayersDraw(final Players players, final Deck deck) {
		for (final Player player : players.toList()) {
			letPlayerDraw(player, deck);
		}
	}

	private void letPlayerDraw(final Player player, final Deck deck) {
		while (isHit(player)) {
			player.drawOneCardFrom(deck);
			printHandOf(player);
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
