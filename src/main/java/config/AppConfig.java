package config;

import controller.BlackjackController;
import view.InputView;
import view.OutputView;

public class AppConfig {

    public BlackjackController blackjackController() {
        return new BlackjackController(inputView(), outputView());
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }

}
