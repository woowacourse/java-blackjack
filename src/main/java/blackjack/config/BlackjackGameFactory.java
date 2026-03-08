package blackjack.config;

import blackjack.controller.BlackjackController;
import blackjack.model.BlackjackGameManager;
import blackjack.model.BlackjackRule;
import blackjack.model.BustPolicy;
import blackjack.model.BustPolicyImpl;
import blackjack.model.DealerHitPolicy;
import blackjack.model.DealerThresholdHitPolicy;
import blackjack.model.PolicyManager;
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
        return new BlackjackService(new ShuffledCardsGenerator(), manager());
    }

    private BlackjackGameManager manager() {
        BustPolicy bustPolicy = bustPolicy();
        DealerHitPolicy dealerHitPolicy = dealerHitPolicy();
        return new BlackjackGameManager(new ScoreCalculator(bustPolicy), rule(bustPolicy, dealerHitPolicy));
    }

    private BlackjackRule rule(BustPolicy bustPolicy, DealerHitPolicy dealerHitPolicy) {
        return new BlackjackRule(
            new PolicyManager(dealerHitPolicy, bustPolicy),
            new ResultJudgement(bustPolicy));
    }

    private BustPolicy bustPolicy() {
        return new BustPolicyImpl(bustThreshold);
    }

    private DealerHitPolicy dealerHitPolicy() {
        return new DealerThresholdHitPolicy(dealerThreshold);
    }
}
