package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.model.card.CardProvider;

public class Application {

    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController(new BlackjackGame(new CardProvider()));
        blackjackController.run();
    }
}
