package blackjack;

import blackjack.controller.BlackJackController;

public class MainApplication {
    public static void main(String[] args) {
        BlackJackController blackJackController = new BlackJackController();
        blackJackController.run();
    }
}
