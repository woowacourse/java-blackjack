package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {
        try {
            BlackjackController blackjackController = new BlackjackController();

            blackjackController.run();
        } catch (Exception e) {
            OutputView.printError(e);
        }
    }
}
