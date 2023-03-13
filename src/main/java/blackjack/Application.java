package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.view.IOView;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(final String[] args) {
        final IOView ioView = new IOView(new InputView(), new OutputView());
        final BlackjackController blackjackController = new BlackjackController(ioView);
        blackjackController.init();
        blackjackController.play();
        blackjackController.calculateResult();
    }
}
