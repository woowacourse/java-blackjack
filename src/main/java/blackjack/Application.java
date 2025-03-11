package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView outputView = new OutputView();

        BlackjackController controller = new BlackjackController(
                new InputView(),
                outputView
        );

        try {
            controller.start();
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }
}
