package blackjackgame;

import blackjackgame.controller.BlackjackGameController;
import blackjackgame.view.InputView;
import blackjackgame.view.OutputView;

public class Application {
    public static void main(String[] args) {
        BlackjackGameController blackjackGameController =
                new BlackjackGameController(new InputView(), new OutputView());
        blackjackGameController.run();
    }
}
