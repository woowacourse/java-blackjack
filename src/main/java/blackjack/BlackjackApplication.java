package blackjack;

import blackjack.domain.BlackjackGame;

public class BlackjackApplication {

    public static void main(String[] args) {
        BlackjackGame blackjackGame = BlackjackGame.init();
        blackjackGame.run();
    }
}
