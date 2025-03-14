package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.manager.BlackJackInitManager;
import blackjack.manager.GameRuleEvaluator;

public class Application {

    public static void main(String[] args) {
        BlackJackInitManager blackJackInitManager = new BlackJackInitManager();
        GameRuleEvaluator gameRuleEvaluator = new GameRuleEvaluator();
        BlackjackController blackjackController = new BlackjackController(gameRuleEvaluator, blackJackInitManager);

        blackjackController.run();
    }
}
