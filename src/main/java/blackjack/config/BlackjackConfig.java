package blackjack.config;

import blackjack.controller.BlackjackController;
import blackjack.model.CardsGenerator;
import blackjack.model.ResultJudgement;
import blackjack.model.UniqueCardsGenerator;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackConfig {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final CardsGenerator cardsGenerator = new UniqueCardsGenerator();
    private final ResultJudgement resultJudgement = new ResultJudgement();

    private final BlackjackController controller = new BlackjackController(
            inputView,
            outputView,
            cardsGenerator,
            resultJudgement
    );

    public BlackjackController controller() {
        return controller;
    }
}
