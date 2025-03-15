package config;

import controller.BlackjackApplication;
import domain.generator.CardGiver;
import domain.generator.CardRandomGenerator;
import view.InputView;
import view.OutputView;
import view.support.InputParser;
import view.support.OutputFormatter;

public class BlackjackConfig {

    public BlackjackApplication blackjackApplication() {
        return new BlackjackApplication(
                inputView(),
                outputView(),
                cardGiver()
        );
    }

    private InputView inputView() {
        return new InputView(new InputParser());
    }

    private OutputView outputView() {
        return new OutputView(new OutputFormatter());
    }

    private CardGiver cardGiver() {
        return new CardGiver(new CardRandomGenerator());
    }
}
