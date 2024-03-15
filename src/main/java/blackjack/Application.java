package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.view.InputView;
import blackjack.view.MessageGenerator;
import blackjack.view.OutputView;
import blackjack.view.validator.InputValidator;

public class Application {

    public static void main(String[] args) {
        final InputView inputView = new InputView(new InputValidator());
        final OutputView outputView = new OutputView(new MessageGenerator());
        final BlackjackController blackjackController = new BlackjackController(inputView, outputView);

        blackjackController.run();
    }
}
