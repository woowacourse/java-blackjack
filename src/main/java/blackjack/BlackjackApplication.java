package blackjack;

import blackjack.config.BlackjackConfig;
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
        BlackjackConfig config = new BlackjackConfig();
        BlackjackController controller = config.controller();

        controller.run();
    }
}
