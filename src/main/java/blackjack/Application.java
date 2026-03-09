package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.model.CardProvider;

public class Application {
    public static void main(String[] args) {

        CardProvider cardProvider = new CardProvider();
        BlackjackController blackjackController = new BlackjackController(cardProvider);
        blackjackController.run();
    }
}
