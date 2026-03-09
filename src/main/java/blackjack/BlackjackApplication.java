package blackjack;

import blackjack.config.BlackjackGameFactory;

public class BlackjackApplication {
    public static void main(String[] args) {
        new BlackjackGameFactory().controller().run();
    }
}
