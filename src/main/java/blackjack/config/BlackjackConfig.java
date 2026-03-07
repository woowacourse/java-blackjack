package blackjack.config;

import blackjack.controller.BlackjackController;
import blackjack.model.AceAdjustPolicy;
import blackjack.model.BustPolicy;
import blackjack.model.BustPolicyImpl;
import blackjack.model.CardsGenerator;
import blackjack.model.DealerDrawPolicy;
import blackjack.model.ResultJudgement;
import blackjack.model.UniqueCardsGenerator;
import blackjack.model.ThresholdDrawPolicy;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackConfig {

    private static final int ADJUST_VALUE = 10;
    private static final int DEALER_DRAW_THRESHOLD = 17;

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final BustPolicy bustPolicy = new BustPolicyImpl();
    private final AceAdjustPolicy aceAdjustPolicy = new AceAdjustPolicy(ADJUST_VALUE, bustPolicy);
    private final DealerDrawPolicy dealerDrawPolicy = new ThresholdDrawPolicy(DEALER_DRAW_THRESHOLD);
    private final CardsGenerator cardsGenerator = new UniqueCardsGenerator();
    private final ResultJudgement resultJudgement = new ResultJudgement(bustPolicy);

    private final BlackjackController controller = new BlackjackController(
            inputView,
            outputView,
            aceAdjustPolicy,
            dealerDrawPolicy,
            bustPolicy,
            cardsGenerator,
            resultJudgement
    );

    public BlackjackController controller() {
        return controller;
    }
}
