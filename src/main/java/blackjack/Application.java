package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.GameManager;

public class Application {

    public static void main(String[] args) {
        BlackjackController controller = new BlackjackController(new GameManager());
        controller.start();
    }
}
