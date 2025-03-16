package blackjack;

import blackjack.game.BlackjackGame;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.Scanner;

public final class Application {

    public static void main(String[] args) {
        final BlackjackGame blackjackGame = makeBlackjackGame();
        blackjackGame.run();
    }

    private static BlackjackGame makeBlackjackGame() {
        final InputView inputView = new InputView(new Scanner(System.in));
        final ResultView resultView = new ResultView();
        return new BlackjackGame(inputView, resultView);
    }
}
