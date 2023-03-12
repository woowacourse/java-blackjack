package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.controller.Retry;
import blackjack.domain.game.BlackjackGame;
import blackjack.view.OutputView;

public final class BlackjackApplication {

    public static void main(String[] args) {
        final BlackjackController blackjackController = new BlackjackController(
                new BlackjackGame(),
                new Retry(5)
        );
        try {
            blackjackController.run();
        } catch (IllegalArgumentException e) {
            final OutputView outputView = new OutputView();
            outputView.printException("재입력 횟수를 초과하여 게임을 종료합니다.");
        }
    }
}
