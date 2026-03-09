package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.model.CardProvider;

public class Application {

    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController(new CardProvider());
        blackjackController.run();
    }
}
