package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.card.CardDeck;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputVIew = new InputView();
        OutputView outputView = new OutputView();
        CardDeck cardDeck = CardDeck.createShuffledDeck();
        BlackjackController controller = new BlackjackController(inputVIew, outputView, cardDeck);
        controller.run();
    }
}
