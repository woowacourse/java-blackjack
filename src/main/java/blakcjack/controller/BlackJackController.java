package blakcjack.controller;

import blakcjack.View.InputView;
import blakcjack.View.OutputView;
import blakcjack.domain.BlackjackGame;
import blakcjack.domain.card.Deck;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Participant;

import java.util.List;

import static blakcjack.View.InputView.takePlayerNamesInput;
import static blakcjack.View.OutputView.printInitialHands;

public class BlackJackController {
	private static final String YES = "y";

	public void run() {
		final List<String> playerNames = takePlayerNamesInput();
		final BlackjackGame blackjackGame = new BlackjackGame(new Deck(), playerNames);
		blackjackGame.initializeHands();

		final List<Participant> players = blackjackGame.getPlayers();
		final Dealer dealer = blackjackGame.getDealer();
		printInitialHands(dealer, players);

		for (final Participant player : players) {
			drawForMaximumCapability(blackjackGame, player);
		}

		drawForMaximumCapability(blackjackGame, dealer);
		OutputView.printFinalHandsSummary(dealer, players);
	}

	private void drawForMaximumCapability(final BlackjackGame blackjackGame, final Participant player) {
		while (player.isScoreLowerThanBlackJackValue() && isYes(player)) {
			blackjackGame.distributeOneCard(player);
			OutputView.printPlayerHand(player);
		}
	}

	private boolean isYes(final Participant player) {
		final String yesOrNo = InputView.takeHitOrStand(player.getName());
		return YES.equals(yesOrNo);
	}

	private void drawForMaximumCapability(final BlackjackGame blackjackGame, final Dealer dealer) {
		while (dealer.isScoreLowerThanSevenTeen()) {
			blackjackGame.distributeOneCard(dealer);
			OutputView.printDealerAdditionalCardMessage();
		}
	}
}
