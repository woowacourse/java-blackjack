package blackjackgame;

import blackjackgame.controller.BlackJackController;
import blackjackgame.view.InputView;
import blackjackgame.view.OutputView;

public class Application {
    public static void main(String[] args) {
        new BlackJackController(new InputView(), new OutputView()).run();
    }
}
