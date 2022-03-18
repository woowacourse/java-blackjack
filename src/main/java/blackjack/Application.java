package blackjack;

import blackjack.controller.GameController;
import blackjack.domain.card.CardDeckGenerator;
import blackjack.domain.card.ShuffleDeckGenerator;
import blackjack.view.ResultView;

public class Application {
    public static void main(String[] args) {
        try {
            GameController gameController = new GameController(new ShuffleDeckGenerator());
            gameController.run();
        } catch (IllegalArgumentException e) {
            ResultView.printErrorMessage(e);
        }
    }
}
