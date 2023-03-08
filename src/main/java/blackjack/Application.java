package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.domain.ShuffledDeckFactory;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(final String[] args) {
        final BlackJackController blackJackController = new BlackJackController(new InputView(), new OutputView());
        blackJackController.generate(new ShuffledDeckFactory());
    }

}
