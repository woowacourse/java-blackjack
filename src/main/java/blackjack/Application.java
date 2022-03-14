package blackjack;

import blackjack.controller.BlackJack;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {
        BlackJack controller = new BlackJack(
                new InputView(), new OutputView()
        );
        controller.run();
    }
}
