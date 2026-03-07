package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.model.cardDeck.PickStrategy;
import blackjack.model.cardDeck.RandomPickStrategy;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        PickStrategy pickStrategy = new RandomPickStrategy();
        BlackjackController blackjackController = new BlackjackController(inputView, outputView, pickStrategy);

        blackjackController.run();
    }
}
