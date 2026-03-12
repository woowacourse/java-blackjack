package blackjack.config;

import blackjack.controller.BlackjackGame;
import blackjack.model.BustPolicy;
import blackjack.model.BustPolicyImpl;
import blackjack.model.DealerHitPolicy;
import blackjack.model.DealerThresholdHitPolicy;
import blackjack.model.ResultJudgement;
import blackjack.model.ScoreCalculator;
import blackjack.model.ShuffledCardsGenerator;
import blackjack.service.BlackjackService;
import blackjack.view.BlackjackView;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackGameFactory {

    private final static int BUST_THRESHOLD = 21;
    private final static int DEALER_HIT_THRESHOLD = 16;

    public BlackjackGame controller() {
        return new BlackjackGame(output(), service());
    }

    private BlackjackView output() {
        return new BlackjackView(new InputView(), new OutputView());
    }

    private BlackjackService service() {
        BustPolicy bustPolicy = bustPolicy();
        DealerHitPolicy dealerHitPolicy = dealerHitPolicy();
        return new BlackjackService(
            new ShuffledCardsGenerator(),
            dealerHitPolicy,
            bustPolicy,
            new ScoreCalculator(bustPolicy),
            new ResultJudgement(bustPolicy)
        );
    }

    private BustPolicy bustPolicy() {
        return new BustPolicyImpl(BUST_THRESHOLD);
    }

    private DealerHitPolicy dealerHitPolicy() {
        return new DealerThresholdHitPolicy(DEALER_HIT_THRESHOLD);
    }
}
