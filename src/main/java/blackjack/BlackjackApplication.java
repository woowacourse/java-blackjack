package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.controller.Retry;
import blackjack.domain.game.BlackjackGame;

public final class BlackjackApplication {

    public static void main(String[] args) {
        final BlackjackController blackjackController = new BlackjackController(
                new BlackjackGame(),
                new Retry(5)
        );
        blackjackController.run();
    }
}
