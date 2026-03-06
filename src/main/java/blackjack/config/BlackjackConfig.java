package blackjack.config;

import blackjack.controller.BlackjackController;
import blackjack.model.AceAdjustPolicy;
import blackjack.model.BustPolicy;
import blackjack.model.BustPolicyImpl;
import blackjack.model.CardsGenerator;
import blackjack.model.DealerDrawPolicy;
import blackjack.model.ResultJudgement;
import blackjack.model.ShuffledCardsGenerator;
import blackjack.model.ThresholdDrawPolicy;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackConfig {

    private static final int ADJUST_VALUE = 10;

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    BustPolicy bustPolicy = new BustPolicyImpl();
    AceAdjustPolicy aceAdjustPolicy = new AceAdjustPolicy(ADJUST_VALUE, bustPolicy);
    DealerDrawPolicy dealerDrawPolicy = new ThresholdDrawPolicy(17);
    CardsGenerator cardsGenerator = new ShuffledCardsGenerator();
    ResultJudgement resultJudgement = new ResultJudgement(bustPolicy);

    BlackjackController controller = new BlackjackController(
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
