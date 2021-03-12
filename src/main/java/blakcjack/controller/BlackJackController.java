package blakcjack.controller;

import blakcjack.domain.blackjackgame.BlackjackGame;
import blakcjack.domain.blackjackgame.GameInitializationFailureException;
import blakcjack.domain.card.Deck;
import blakcjack.domain.card.EmptyDeckException;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.shufflestrategy.RandomShuffleStrategy;
import blakcjack.exception.GameTerminationException;
import blakcjack.view.InputView;

import java.util.List;

import static blakcjack.view.InputView.takePlayerNamesInput;
import static blakcjack.view.OutputView.printDealerAdditionalCardMessage;
import static blakcjack.view.OutputView.printFinalHandsSummary;
import static blakcjack.view.OutputView.printFinalOutcomeSummary;
import static blakcjack.view.OutputView.printGameClosing;
import static blakcjack.view.OutputView.printInitialHands;
import static blakcjack.view.OutputView.printPlayerHand;

public class BlackJackController {
    public void run() {
        final BlackjackGame blackjackGame = initializeGame();
        final List<Participant> players = blackjackGame.getPlayers();
        final Dealer dealer = (Dealer) blackjackGame.getDealer();

        drawInitialCards(blackjackGame);
        printInitialHands(dealer, players);

        drawCardsInTurn(blackjackGame, players, dealer);

        notifyFinalSummary(blackjackGame, players, dealer);
    }

    private BlackjackGame initializeGame() {
        try {
            return new BlackjackGame(new Deck(new RandomShuffleStrategy()), takePlayerNamesInput());
        } catch (GameInitializationFailureException e) {
            printGameClosing(e.getMessage());
            throw new GameTerminationException();
        }
    }

    private void drawInitialCards(final BlackjackGame blackjackGame) {
        try {
            blackjackGame.initializeHands();
        } catch (final EmptyDeckException e) {
            printGameClosing(e.getMessage());
            throw new GameTerminationException();
        }
    }

    private void drawCardsInTurn(final BlackjackGame blackjackGame,
                                 final List<Participant> players, final Dealer dealer) {
        try {
            drawForMaximumCapability(blackjackGame, players);
            drawForMaximumCapability(blackjackGame, dealer);
        } catch (final EmptyDeckException e) {
            printGameClosing(e.getMessage());
            throw new GameTerminationException();
        }
    }

    private void drawForMaximumCapability(final BlackjackGame blackjackGame, final List<Participant> players) {
        for (final Participant player : players) {
            drawForMaximumCapability(blackjackGame, player);
        }
    }

    private void drawForMaximumCapability(final BlackjackGame blackjackGame, final Participant player) {
        while (player.canDrawMoreCard() && isHitSelected(player)) {
            blackjackGame.distributeOneCard(player);
            printPlayerHand(player);
        }
    }

    private boolean isHitSelected(final Participant player) {
        return InputView.takeHitOrStand(player.getNameValue());
    }

    private void drawForMaximumCapability(final BlackjackGame blackjackGame, final Dealer dealer) {
        while (dealer.needsAdditionalCard()) {
            blackjackGame.distributeOneCard(dealer);
            printDealerAdditionalCardMessage();
        }
    }

    private void notifyFinalSummary(final BlackjackGame blackjackGame, final List<Participant> players, final Dealer dealer) {
        printFinalHandsSummary(dealer, players);
        printFinalOutcomeSummary(blackjackGame.judgeOutcome(), dealer.getNameValue());
    }
}
