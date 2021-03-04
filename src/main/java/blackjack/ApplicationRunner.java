package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Cards;

public class ApplicationRunner {

    public static void main(String[] args) {
        Cards cards = new Cards(CardFactory.getNormalCards());
        cards.shuffle();
        new BlackjackController(cards).run();
    }

}
