package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        for (Shape shape : Shape.values()) {
            getMakeCardsOf(shape);
        }
    }

    private void getMakeCardsOf(final Shape shape) {
        for (int i = CardNumber.getMinValue(); i <= CardNumber.getMaxValue(); i++) {
            cards.add(new Card(shape, CardNumber.of(i)));
        }
    }

    public Card drawCard() {
        return cards.get(0);
    }
}
