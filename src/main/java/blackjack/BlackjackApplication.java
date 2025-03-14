package blackjack;

import blackjack.controller.BlackjackGame;

public class BlackjackApplication {

    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.run();
    }
}
