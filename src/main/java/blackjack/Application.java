package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.strategy.RandomShuffleStrategy;
import blackjack.strategy.ShuffleStrategy;

public class Application {

    public static void main(String[] args) {
        ShuffleStrategy shuffleStrategy = new RandomShuffleStrategy();
        BlackjackController blackjackController = new BlackjackController(shuffleStrategy);
        blackjackController.run();
    }
}
