package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.factory.DeckFactory;
import blackjack.factory.SingDeckGenerator;

public class Application {

    public static void main(String[] args) {
        DeckFactory deckFactory = new DeckFactory(new SingDeckGenerator());
        BlackjackController blackjackController = new BlackjackController(deckFactory);

        blackjackController.run();
    }
}