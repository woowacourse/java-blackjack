package blackjack.config;

import blackjack.controller.BlackjackController;
import blackjack.model.BlackjackRule;
import blackjack.model.BustPolicy;
import blackjack.model.BustPolicyImpl;
import blackjack.model.DealerThresholdHitPolicy;
import blackjack.model.ResultJudgement;
import blackjack.model.ScoreCalculator;
import blackjack.model.ShuffledCardsGenerator;
import blackjack.service.BlackjackService;
import blackjack.view.BlackjackView;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackGameFactory {

    private final int bustThreshold;
    private final int dealerThreshold;

    public BlackjackGameFactory(int bustThreshold, int dealerThreshold) {
        this.bustThreshold = bustThreshold;
        this.dealerThreshold = dealerThreshold;
    }

    public BlackjackController controller() {
        return new BlackjackController(output(), service());
    }

    private BlackjackView output() {
        return new BlackjackView(new InputView(), new OutputView());
    }

    private BlackjackService service() {
        return new BlackjackService(new ShuffledCardsGenerator(), rule());
    }

    private BlackjackRule rule() {
        BustPolicy bustPolicy = bustPolicy();
        return new BlackjackRule(
            new ScoreCalculator(bustPolicy),
            new DealerThresholdHitPolicy(dealerThreshold),
            bustPolicy,
            new ResultJudgement(bustPolicy));
    }

    private BustPolicy bustPolicy() {
        return new BustPolicyImpl(bustThreshold);
    }
}
