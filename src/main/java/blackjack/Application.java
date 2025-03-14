package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.factory.SingleDeckFactory;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {
        try {
            SingleDeckFactory deckFactory = new SingleDeckFactory();
            BlackjackController blackjackController = new BlackjackController(deckFactory);

            blackjackController.run();
        } catch (Exception e) {
            OutputView.printError(e);
        }
    }
}
