package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.model.Deck;
import blackjack.model.GameResultCalculator;

public class Application {
    public static void main(String[] args) {

        Deck deck = new Deck();
        GameResultCalculator gameResultCalculator = new GameResultCalculator();
        BlackjackController blackjackController = new BlackjackController(deck, gameResultCalculator);
        blackjackController.run();

    }
}

