package blakcjack.controller;

import blakcjack.domain.blackjackgame.BlackjackGame;
import blakcjack.domain.card.Deck;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Participant;
import blakcjack.view.InputView;
import blakcjack.view.OutputView;

import java.util.List;

import static blakcjack.view.InputView.takePlayerNamesInput;
import static blakcjack.view.OutputView.printInitialHands;

public class BlackJackController {
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
        OutputView.printFinalOutcomeSummary(blackjackGame.judgeOutcome(), dealer.getName());
    }

    private void drawForMaximumCapability(final BlackjackGame blackjackGame, final Participant player) {
        while (player.isScoreLowerThanBlackJackValue() && isHitSelected(player)) {
            blackjackGame.distributeOneCard(player);
            OutputView.printPlayerHand(player);
        }
    }

    private boolean isHitSelected(final Participant player) {
        return InputView.takeHitOrStand(player.getName());
    }

    private void drawForMaximumCapability(final BlackjackGame blackjackGame, final Dealer dealer) {
        while (dealer.isScoreLowerThanSevenTeen()) {
            blackjackGame.distributeOneCard(dealer);
            OutputView.printDealerAdditionalCardMessage();
        }
    }
}
