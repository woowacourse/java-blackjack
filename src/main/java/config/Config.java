package config;

import controller.BlackJackController;
import view.InputView;
import view.OutputView;

public class Config {
    private static InputView inputView;
    private static OutputView outputView;
    private static BlackJackController blackJackController;

    public BlackJackController blackJackController() {
        if (blackJackController == null) {
            return new BlackJackController(inputView(), outputView());
        }
        return blackJackController;
    }

    private InputView inputView() {
        if (inputView == null) {
            return new InputView();
        }
        return inputView;
    }

    private OutputView outputView() {
        if (outputView == null) {
            return new OutputView();
        }
        return outputView;
    }
}
