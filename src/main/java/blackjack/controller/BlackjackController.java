package blackjack.controller;

import blackjack.domain.Game;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void run() {
        Game game = new Game(InputView.inputPlayerNames());
        game.initialCards();
        OutputView.printInitialCards(game.getDealer(), game.getPlayers());
    }
}

