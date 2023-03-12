package blackjack;

import blackjack.controller.BlackJackGameController;
import blackjack.domain.card.ShufflingMachine;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(final String[] args) {
        final BlackJackGameController blackJackGameController =
                new BlackJackGameController(new InputView(), new OutputView(), new ShufflingMachine());
        blackJackGameController.run();
    }
}
