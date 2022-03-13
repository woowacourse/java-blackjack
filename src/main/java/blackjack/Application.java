package blackjack;

import blackjack.controller.BlackjackController;

public class Application {

    public static void main(String[] args) {
        BlackjackController controller = new BlackjackController();
        controller.run();
    }
}
