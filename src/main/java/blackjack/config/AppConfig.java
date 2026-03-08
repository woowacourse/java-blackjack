package blackjack.config;

import blackjack.controller.BlackjackController;
import blackjack.view.InputView;

public class AppConfig {

    private BlackjackController controller;
    private InputView inputView;

    public AppConfig() {
    }

    public BlackjackController controller() {
        if (controller == null) {
            this.controller = new BlackjackController(inputView());
        }
        return controller;
    }

    private InputView inputView() {
        if (inputView == null) {
            this.inputView = new InputView();
        }
        return inputView;
    }

}
