package blackjack.controller;

import blackjack.domain.Game;
import blackjack.view.InputView;

public class BlackjackController {

    public void run() {
        Game game = new Game(InputView.inputPlayerNames());
    }
}
