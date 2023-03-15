package blackjack;

import blackjack.controller.BlackJackController;

public class Application {
    public static void main(String[] args) {
        BlackJackController blackJackController = new BlackJackController();
        blackJackController.playGame();
    }
}
