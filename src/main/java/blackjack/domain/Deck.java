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
        try {
            return cards.remove(0);
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalStateException("카드 업슝", exception);
        }
    }

}
