package blackjack;

import blackjack.view.GameView;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackApplication {

    public static void main(String[] args) {
        GameView gameView = new GameView(
            new InputView(),
            new OutputView()
        );
        gameView.displayToConsole();
    }
}
