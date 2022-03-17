package blackjack;

import blackjack.controller.GameController;
import blackjack.view.ResultView;

public class Application {
    public static void main(String[] args) {
        try {
            GameController gameController = new GameController();
            gameController.run();
        } catch (IllegalArgumentException e) {
            ResultView.printErrorMessage(e);
        }
    }
}
