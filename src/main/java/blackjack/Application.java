package blackjack;

import blackjack.controller.BlackjackGame;

public class Application {
    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.run();
    }
}
