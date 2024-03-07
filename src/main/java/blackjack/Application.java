package blackjack;

import blackjack.domain.controller.BlackjackController;

public class Application {
    public static void main(String[] args) {
        new BlackjackController().run();
    }
}
