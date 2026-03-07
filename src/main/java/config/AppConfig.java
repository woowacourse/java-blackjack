package config;

import controller.BlackjackController;
import converter.BlackjackConverter;
import service.BlackjackService;
import view.InputView;
import view.OutputView;

public class AppConfig {

    public BlackjackController blackjackController() {
        return new BlackjackController(inputView(), outputView(), blackjackService());
    }

    public BlackjackService blackjackService() {
        return new BlackjackService(blackjackConverter());
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }

    public BlackjackConverter blackjackConverter() {
        return new BlackjackConverter();
    }
}
