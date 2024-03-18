package blackjack;

import blackjack.controller.BlackjackController;

public class Application {
    public static void main(final String[] args) {
        new BlackjackController().runBlackjack();
    }
}
