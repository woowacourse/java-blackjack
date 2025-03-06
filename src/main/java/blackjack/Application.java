package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.manager.GameRuleEvaluator;

public class Application {

    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController(new GameRuleEvaluator());

        blackjackController.run();
    }
}
