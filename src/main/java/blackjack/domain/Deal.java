package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Deal {

    private static final int INIT_CARD_COUNT = 2;

    private final Cards cards = new Cards();

    public Deal() {
        cards.generate();
    }

    public List<Card> dealInit() {
        List<Card> givenCards = new ArrayList<>();
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            givenCards.add(cards.dealCard());
        }
        return givenCards;
    }

    public Card deal() {
        return cards.dealCard();
    }
}
