package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.model.card.initializer.DefaultCardDeckInitializer;
import blackjack.model.game.BlackJackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {
        BlackJackController blackJackController =
                new BlackJackController(
                        new InputView(),
                        new OutputView(),
                        new BlackJackGame(new DefaultCardDeckInitializer())
                );

        blackJackController.run();
    }

}
