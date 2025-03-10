package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.manager.BlackJackInitManager;
import blackjack.manager.CardsGenerator;

public class Application {

    public static void main(String[] args) {
        BlackJackInitManager blackJackInitManager = new BlackJackInitManager(new CardsGenerator());
        BlackjackController blackjackController = new BlackjackController(blackJackInitManager);

        blackjackController.run();
    }
}
