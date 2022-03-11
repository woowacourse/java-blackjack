package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public class TestBlackjackUtils {

    public static Hand createCardHand(Card... card) {
        Hand cardHand = new Hand();
        cardHand.add(card);
        return cardHand;
    }
}
