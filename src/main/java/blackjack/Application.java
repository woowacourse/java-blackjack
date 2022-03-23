package blackjack;

import blackjack.controller.BlackjackGame;

public final class Application {
    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.run();
    }
}
