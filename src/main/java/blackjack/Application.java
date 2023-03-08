package blackjack;

import blackjack.controller.BlackJackGameController;

public class Application {
    public static void main(String[] args) {
        BlackJackGameController blackJackGameController = new BlackJackGameController();
        blackJackGameController.run();
    }
}
