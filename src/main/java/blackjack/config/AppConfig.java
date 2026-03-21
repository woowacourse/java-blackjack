package blackjack.config;

import blackjack.controller.BlackjackController;
import blackjack.domain.deck.CollectionsShuffle;
import blackjack.domain.deck.Deck;
import blackjack.view.input.ConsoleInputView;
import blackjack.view.input.InputView;
import blackjack.view.output.ConsoleOutputView;
import blackjack.view.output.OutputView;

public class AppConfig {

    private BlackjackController controller;
    private InputView inputView;
    private OutputView outputView;
    private Deck deck;

    public BlackjackController controller() {
        if (controller == null) {
            controller = new BlackjackController(inputView(), outputView(), deck());
        }
        return controller;
    }

    private InputView inputView() {
        if (inputView == null) {
            inputView = new ConsoleInputView();
        }
        return inputView;
    }

    private OutputView outputView() {
        if (outputView == null) {
            outputView = new ConsoleOutputView();
        }
        return outputView;
    }

    private Deck deck() {
        if (deck == null) {
            deck = Deck.createWithShuffled(new CollectionsShuffle());
        }
        return deck;
    }

}
