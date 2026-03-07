package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.model.BustPolicy;
import blackjack.model.BustPolicyImpl;
import blackjack.model.DealerThresholdHitPolicy;
import blackjack.model.ResultJudgement;
import blackjack.model.ScoreCalculator;
import blackjack.model.ShuffledCardsGenerator;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BustPolicy bustPolicy = new BustPolicyImpl(21);
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        DealerThresholdHitPolicy dealerThresholdHitPolicy = new DealerThresholdHitPolicy(17);
        ShuffledCardsGenerator shuffledCardsGenerator = new ShuffledCardsGenerator();
        ResultJudgement resultJudgement = new ResultJudgement(bustPolicy);

        BlackjackController controller = new BlackjackController(
            inputView,
            outputView,
            scoreCalculator,
            dealerThresholdHitPolicy,
            bustPolicy,
            shuffledCardsGenerator,
            resultJudgement
        );

        controller.run();
    }
}
