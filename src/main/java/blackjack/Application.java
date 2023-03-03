package blackjack;

import blackjack.domain.BlackJackManager;

public class Application {
    public static void main(String[] args) {
        BlackJackManager blackJackManager = new BlackJackManager();
        blackJackManager.run();
    }
}
