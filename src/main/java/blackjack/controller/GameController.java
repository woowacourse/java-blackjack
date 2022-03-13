package blackjack.controller;

import blackjack.domain.card.Status;
import blackjack.domain.participant.Player;
import blackjack.service.Casino;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class GameController {

    public void play() {
        final Casino casino = crateCasino();
        casino.prepareParticipants();

        while (casino.isPlayerTurn()) {
            final Player player = casino.findDrawablePlayer();
            final Status status = InputView.getHitOrStay(player.getName());
            casino.progressPlayerTurn(player, status);
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