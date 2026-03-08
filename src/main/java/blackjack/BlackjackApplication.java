package blackjack;

import blackjack.config.BlackjackGameFactory;
import blackjack.controller.BlackjackController;
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

public class BlackjackApplication {

    public static void main(String[] args) {
        BlackjackGameFactory factory = new BlackjackGameFactory(21, 16);
        factory.controller().run();
    }
}
