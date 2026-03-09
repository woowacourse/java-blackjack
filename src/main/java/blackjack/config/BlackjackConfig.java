package blackjack.config;

import blackjack.controller.BlackjackController;
import blackjack.model.ResultJudgement;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackConfig {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final ResultJudgement resultJudgement = new ResultJudgement();

    private final BlackjackController controller = new BlackjackController(
            inputView,
            outputView,
            resultJudgement
    );

    public BlackjackController controller() {
        return controller;
    }
}
