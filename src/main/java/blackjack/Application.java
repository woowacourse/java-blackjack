package blackjack;

import blackjack.controller.BlackjackGame;

public class Application {

    public static void main(String[] args) {
        BlackjackGame blackJackGame = new BlackjackGame();
        blackJackGame.play();
    }
}
