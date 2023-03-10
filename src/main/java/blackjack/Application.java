package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.view.InputView;

public class Application {
    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController();
        blackjackController.run();
        InputView.closeScanner();
    }
}
