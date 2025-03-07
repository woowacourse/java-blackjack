package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.manager.BlackJackInitManager;
import blackjack.manager.CardsGenerator;
import blackjack.manager.GameRuleEvaluator;

public class Application {

    public static void main(String[] args) {
        BlackJackInitManager blackJackInitManager = new BlackJackInitManager(new CardsGenerator());
        GameRuleEvaluator gameRuleEvaluator = new GameRuleEvaluator();
        BlackjackController blackjackController = new BlackjackController(gameRuleEvaluator, blackJackInitManager);

        blackjackController.run();
    }
}
