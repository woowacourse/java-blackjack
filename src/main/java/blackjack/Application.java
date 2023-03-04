package blackjack;

import blackjack.controller.BlackJackGameController;
import blackjack.domain.card.ShufflingMachine;

public class Application {

    public static void main(final String[] args) {
        final BlackJackGameController blackJackGameController = new BlackJackGameController(new ShufflingMachine());
        blackJackGameController.run();
    }
}
