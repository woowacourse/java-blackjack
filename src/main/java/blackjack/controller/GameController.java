package blackjack.controller;

import blackjack.domain.card.Status;
import blackjack.domain.participant.Player;
import blackjack.service.Casino;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class GameController {

    public void play() {
        final Casino casino = new Casino(getPlayerNames());
        casino.prepareParticipants();

        while (casino.isPlayerTurn()) {
            final Player player = casino.findDrawablePlayer();
            final Status status = getHitOrStay(player);
            casino.progressPlayerTurn(player, status);
        }
        casino.progressDealerTurn();

        casino.endGame();
    }

    private List<String> getPlayerNames() {
        try {
            return InputView.requestPlayerNames();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return getPlayerNames();
        }
    }

    private Status getHitOrStay(final Player player) {
        try {
            return InputView.requestHitOrStay(player.getName());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return getHitOrStay(player);
        }
    }
}