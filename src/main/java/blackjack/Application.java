package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.factory.SingleDeckFactory;

public class Application {

    public static void main(String[] args) {
        SingleDeckFactory deckFactory = new SingleDeckFactory();
        BlackjackController blackjackController = new BlackjackController(deckFactory);

        blackjackController.run();
    }
}