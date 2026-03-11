package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.model.Deck;
import blackjack.model.RandomShuffleStrategy;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Deck deck = new Deck(new RandomShuffleStrategy());

        BlackjackController blackjackController = new BlackjackController(inputView, outputView, deck);
        blackjackController.run();
    }
}

