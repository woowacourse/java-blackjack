package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.GameManager;
import blackjack.domain.RandomBlackjackShuffle;

public class Application {

    public static void main(String[] args) {
        BlackjackController controller = new BlackjackController(new GameManager(new RandomBlackjackShuffle()));
        controller.start();
    }
}
