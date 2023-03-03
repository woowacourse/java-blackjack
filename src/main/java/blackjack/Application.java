package blackjack;

import blackjack.controller.GameController;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {
        GameController gameController = new GameController(InputView.getInstance(), OutputView.getInstance());
        gameController.run();

    }
}
