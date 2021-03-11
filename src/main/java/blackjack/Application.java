package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.domain.BlackJackGame;

public class Application {
    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame();
        BlackJackController blackJackController = new BlackJackController(blackJackGame);
        blackJackController.start();
    }
}
