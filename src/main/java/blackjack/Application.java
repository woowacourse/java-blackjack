package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.manager.GameRuleEvaluator;

public class Application {

    public static void main(String[] args) {
        GameRuleEvaluator gameRuleEvaluator = new GameRuleEvaluator();
        BlackjackController blackjackController = new BlackjackController(gameRuleEvaluator);

        blackjackController.run();
    }
}
