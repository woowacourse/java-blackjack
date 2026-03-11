package blackjack.config;

import blackjack.controller.BlackjackController;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class AppConfig {

    private BlackjackController controller;
    private InputView inputView;
    private OutputView outputView;

    public AppConfig() {
    }

    public BlackjackController controller() {
        if (controller == null) {
            this.controller = new BlackjackController(inputView(), outputView());
        }
        return controller;
    }

    private InputView inputView() {
        if (inputView == null) {
            this.inputView = new InputView();
        }
        return inputView;
    }

    private OutputView outputView() {
        if (outputView == null) {
            this.outputView = new OutputView();
        }
        return outputView;
    }

}
