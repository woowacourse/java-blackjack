package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.strategy.RandomCardShuffle;

public class Application {

    public static void main(String[] args) {
        final BlackJackController blackJackController = new BlackJackController();

        blackJackController.run();
    }
}
