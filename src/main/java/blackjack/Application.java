package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.model.CardCalculator;
import blackjack.model.CardProvider;
import blackjack.model.GameResultCalculator;

public class Application {
    public static void main(String[] args) {

        CardProvider cardProvider = new CardProvider();
        CardCalculator cardCalculator = new CardCalculator();
        GameResultCalculator gameResultCalculator = new GameResultCalculator();
        BlackjackController blackjackController = new BlackjackController(cardProvider, cardCalculator,
                gameResultCalculator);
        blackjackController.run();

    }
}

