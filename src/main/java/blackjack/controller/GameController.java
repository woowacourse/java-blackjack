package blackjack.controller;

import blackjack.domain.card.Status;
import blackjack.service.Casino;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class GameController {

    public void play() {
        final Casino casino = crateCasino();
        casino.prepareParticipants();

        while (casino.isPlayerTurn()) {
            final String playerName = casino.findDrawablePlayerName();
            final Status status = InputView.getHitOrStay(playerName);
            casino.progressPlayerTurn(playerName, status);
        }
        casino.progressDealerTurn();

        casino.endGame();
    }

    private Casino crateCasino() {
        try {
            return new Casino(InputView.getPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return crateCasino();
        }
    }
}