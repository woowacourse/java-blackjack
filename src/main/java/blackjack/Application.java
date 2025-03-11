package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.controller.BlackjackInitManager;
import blackjack.factory.SingDeckGenerator;

public class Application {

    public static void main(String[] args) {
        BlackjackInitManager blackJackInitManager = new BlackjackInitManager(new SingDeckGenerator());
        BlackjackController blackjackController = new BlackjackController(blackJackInitManager);

        blackjackController.run();
    }
}
