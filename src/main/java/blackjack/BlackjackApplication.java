package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.controller.Retry;
import blackjack.domain.game.BlackjackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public final class BlackjackApplication {

    public static void main(String[] args) {
        final BlackjackController blackjackController = new BlackjackController(
                new InputView(),
                new OutputView(),
                new BlackjackGame()
        );
        try {
            blackjackController.run(new Retry(5));
        } catch (IllegalArgumentException e) {
            final OutputView outputView = new OutputView();
            outputView.printException(Retry.EXCEPTION_MESSAGE);
        }
    }
}
