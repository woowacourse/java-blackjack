package blackjackgame;

import blackjackgame.controller.BlackjackController;
import blackjackgame.domain.card.Deck;

public class Main {

    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController();
        blackjackController.startBlackjackGame(Deck.fullDeck());
    }
}
