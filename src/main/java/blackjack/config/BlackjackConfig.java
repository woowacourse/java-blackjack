package blackjack.config;

import blackjack.controller.BlackjackController;
import blackjack.model.CardsGenerator;
import blackjack.model.DealerDrawPolicy;
import blackjack.model.ResultJudgement;
import blackjack.model.ThresholdDrawPolicy;
import blackjack.model.UniqueCardsGenerator;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackConfig {

    private static final int DEALER_DRAW_THRESHOLD = 17;

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final DealerDrawPolicy dealerDrawPolicy = new ThresholdDrawPolicy(DEALER_DRAW_THRESHOLD);
    private final CardsGenerator cardsGenerator = new UniqueCardsGenerator();
    private final ResultJudgement resultJudgement = new ResultJudgement();

    private final BlackjackController controller = new BlackjackController(
            inputView,
            outputView,
            dealerDrawPolicy,
            cardsGenerator,
            resultJudgement
    );

    public BlackjackController controller() {
        return controller;
    }
}
