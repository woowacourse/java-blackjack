package config;

import controller.BlackjackApplication;
import view.InputView;
import view.OutputView;
import view.support.InputParser;
import view.support.OutputFormatter;

public class BlackjackConfig {

    public BlackjackApplication blackjackApplication() {
        return new BlackjackApplication(
                inputView(),
                outputView()
        );
    }

    private InputView inputView() {
        return new InputView(new InputParser());
    }

    private OutputView outputView() {
        return new OutputView(new OutputFormatter());
    }
}
