package blackjack.config;

import blackjack.controller.BlackjackController;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class AppConfig {
    public BlackjackController blackjackController(){
        return new BlackjackController(inputView(), outputView());
    }

    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }
}
