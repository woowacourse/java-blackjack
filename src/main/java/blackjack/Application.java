package blackjack;

import blackjack.controller.BlackjackController;

public class Application {

    public static void main(String[] args) {
        BlackjackController blackJackController = new BlackjackController();
        blackJackController.run();
    }
}
