package blackjackgame;

import blackjackgame.controller.BlackJackGameController;
import blackjackgame.view.InputView;
import blackjackgame.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputview = new InputView();
        OutputView outputView = new OutputView();
        BlackJackGameController blackJackGameController = new BlackJackGameController(inputview, outputView);
        blackJackGameController.run();
    }
}
