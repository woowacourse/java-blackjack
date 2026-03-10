package blackjack.config;

import blackjack.controller.BlackjackController;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackConfig {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final BlackjackController controller = new BlackjackController(
            inputView,
            outputView
    );

    public BlackjackController controller() {
        return controller;
    }
}
