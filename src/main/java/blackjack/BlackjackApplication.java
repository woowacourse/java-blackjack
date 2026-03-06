package blackjack;

import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackApplication {

    public static void main(final String[] args) {
        BlackjackGame game = new BlackjackGame(new InputView(), new OutputView());
        game.start();
    }
}
