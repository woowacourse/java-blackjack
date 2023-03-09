package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.strategy.RandomCardShuffle;

public class Application {

    public static void main(String[] args) {
        final RandomCardShuffle randomCardShuffle = new RandomCardShuffle();
        final BlackJackController blackJackController = new BlackJackController(randomCardShuffle);

        blackJackController.run();
    }
}
