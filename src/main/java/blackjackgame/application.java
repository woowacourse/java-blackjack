package blackjackgame;

import blackjackgame.controller.blackJackController;
import blackjackgame.view.InputView;
import blackjackgame.view.OutputView;

public class application {
    public static void main(String[] args) {
        new blackJackController(new InputView(), new OutputView()).run();
    }
}
