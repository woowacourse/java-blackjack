package blackjack;

import blackjack.controller.BlackJackController;

public class BlackJackApplication {

    public static void main(final String... args) {
        BlackJackController blackJackController = new BlackJackController();
        blackJackController.run();
    }
}
