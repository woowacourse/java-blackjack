package blackjack.domain.card;

import java.util.List;

public class JustTwoSpadeDeck implements Deck {

    @Override
    public Card pick() {
        return Card.of(CardNumber.TWO, Type.SPADE);
    }
}
