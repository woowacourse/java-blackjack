package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.model.Deck;

public class Application {
    public static void main(String[] args) {

        Deck deck = new Deck();
        BlackjackController blackjackController = new BlackjackController(deck);
        blackjackController.run();

    }
}

