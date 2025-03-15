package blackjack;

import blackjack.view.InputView;
import blackjack.view.ResultView;

public class Application {

    public static void main(String[] args) {
        final BlackjackGame blackjackGame = makeBlackjackGame();
        blackjackGame.run();
    }

    private static BlackjackGame makeBlackjackGame() {
        final InputView inputView = new InputView();
        final ResultView resultView = new ResultView();
        return new BlackjackGame(inputView, resultView);
    }
}
