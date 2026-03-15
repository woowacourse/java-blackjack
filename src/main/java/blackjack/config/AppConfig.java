package blackjack.config;

import blackjack.controller.BlackjackController;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class AppConfig {
    public BlackjackController blackjackController(){
        return new BlackjackController(new InputView(), new OutputView());
    }
}
