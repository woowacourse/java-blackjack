package blackjack;

import blackjack.controller.BlackJackGame;

public class Application {
    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.start();
    }
}
