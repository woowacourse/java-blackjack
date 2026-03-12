package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.deck.Deck;
import blackjack.service.GameService;
import blackjack.view.InputView;

public class Application {

    public static void main(String[] args) {
        BlackjackController controller = new BlackjackController(new GameService(new Deck()), new InputView());
        controller.run();
    }
}
