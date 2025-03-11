package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.manager.BlackJackInitManager;
import blackjack.manager.SingDeckGenerator;

public class Application {

    public static void main(String[] args) {
        BlackJackInitManager blackJackInitManager = new BlackJackInitManager(new SingDeckGenerator());
        BlackjackController blackjackController = new BlackjackController(blackJackInitManager);

        blackjackController.run();
    }
}
