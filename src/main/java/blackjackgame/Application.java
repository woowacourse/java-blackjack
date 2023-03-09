package blackjackgame;

import blackjackgame.controller.BlackJackController;
import blackjackgame.view.InputView;
import blackjackgame.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final BlackJackController blackJackController = new BlackJackController(inputView, outputView);

        blackJackController.run();
    }
}
