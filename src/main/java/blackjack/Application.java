package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.domain.card.Deck;

public class Application {
    public static void main(String[] args) {
        final Deck deck = new Deck();
        final BlackJackController blackJackController = new BlackJackController(deck);

        blackJackController.showParticipantsInitCards();

        blackJackController.inputHitCondition();
        blackJackController.showParticipantsStatus();

        blackJackController.showGameResult();
    }
}
