package config;

import static factory.BlackJackCreator.createCardBundle;

import controller.BlackJackController;
import view.InputView;
import view.OutputView;

public class BlackJackConfig {

    public static BlackJackController createBlackJackController() {
        return new BlackJackController(new InputView(), new OutputView(), createCardBundle());
    }
}
