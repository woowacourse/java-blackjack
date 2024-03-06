package blackjack;

import blackjack.view.InputView;
import blackjack.view.OutputView;

class Application {

    public static void main(String[] args) {
        new Controller(
                new InputView(),
                new OutputView()
        ).run();
    }
}
