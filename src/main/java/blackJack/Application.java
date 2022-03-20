package blackJack;

import blackJack.controller.BlackjackController;

public class Application {

    public static void main(String[] args) {
        final BlackjackController blackjackController = new BlackjackController();
        blackjackController.run();
    }
}
