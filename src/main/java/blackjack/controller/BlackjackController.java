package blackjack.controller;

import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void run() {
        OutputView.printPlayerNameInstruction();
        Players players = createPlayers();
    }

    private Players createPlayers() {
        try {
            return new Players(InputView.inputPlayerName());
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return createPlayers();
        }
    }
}