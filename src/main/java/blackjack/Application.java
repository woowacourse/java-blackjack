package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.view.InputView;
import blackjack.view.GameView;

public class Application {

    public static void main(String[] args) {
        BlackjackController blackJackController = new BlackjackController(
            new InputView(),
            new GameView()
        );
        blackJackController.start();
    }
}
