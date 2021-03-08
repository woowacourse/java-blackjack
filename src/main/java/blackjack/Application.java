package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.BlackjackGame;

public class Application {
    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController(new BlackjackGame());
        blackjackController.run();
    }
}
