package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.model.DeckInitializer;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BlackJackController blackJackController = new BlackJackController(
                inputView,
                outputView,
                new DeckInitializer()
        );
        blackJackController.run();
    }
}
