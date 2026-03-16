package config;

import controller.BlackJackController;
import service.BlackJackInitService;
import service.BlackJackTurnService;
import view.InputView;
import view.OutputView;

public class AppConfig {
    public BlackJackInitService blackJackInitService() {
        return new BlackJackInitService();
    }

    public BlackJackTurnService blackJackTurnService() {
        return new BlackJackTurnService();
    }

    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }

    public BlackJackController blackJackController() {
        return new BlackJackController(
                blackJackInitService(),
                blackJackTurnService(),
                inputView(),
                outputView());
    }
}
