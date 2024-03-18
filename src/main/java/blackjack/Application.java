package blackjack;

import blackjack.controller.BlackJackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {
        BlackJackGame controller = new BlackJackGame(new InputView(), new OutputView());
        controller.run();
    }
}
