package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.model.AceAdjustPolicy;
import blackjack.model.BustPolicy;
import blackjack.model.BustPolicyImpl;
import blackjack.model.ResultJudgement;
import blackjack.model.ShuffledCardsGenerator;
import blackjack.model.ThresholdDrawPolicy;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BustPolicy bustPolicy = new BustPolicyImpl();
        AceAdjustPolicy aceAdjustPolicy = new AceAdjustPolicy(bustPolicy);
        ThresholdDrawPolicy drawPolicy = new ThresholdDrawPolicy(17);
        ShuffledCardsGenerator shuffledCardsGenerator = new ShuffledCardsGenerator();
        ResultJudgement resultJudgement = new ResultJudgement(bustPolicy);

        BlackjackController controller = new BlackjackController(
                inputView,
                outputView,
                aceAdjustPolicy,
                drawPolicy,
                bustPolicy,
                shuffledCardsGenerator,
                resultJudgement
        );

        controller.run();
    }
}
