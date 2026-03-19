package blackjack.config;

import blackjack.controller.BlackjackController;
import blackjack.view.ConsoleInputView;
import blackjack.view.ConsoleOutputView;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class AppConfig {

    private BlackjackController controller;
    private InputView inputView;
    private OutputView outputView;

    public BlackjackController controller() {
        if (controller == null) {
            controller = new BlackjackController(inputView(), outputView());
        }
        return controller;
    }

    private InputView inputView() {
        if (inputView == null) {
            inputView = new ConsoleInputView();
        }
        return inputView;
    }

    private OutputView outputView() {
        if (outputView == null) {
            outputView = new ConsoleOutputView();
        }
        return outputView;
    }

}
