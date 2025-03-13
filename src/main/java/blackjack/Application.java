package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.view.InputView;
import blackjack.view.ResultView;

public class Application {

    public static void main(String[] args) {
        final BlackjackController blackjackController = makeController();
        blackjackController.run();
    }

    private static BlackjackController makeController() {
        final InputView inputView = new InputView();
        final ResultView resultView = new ResultView();
        return new BlackjackController(inputView, resultView);
    }
}
