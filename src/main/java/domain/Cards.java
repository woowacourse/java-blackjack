package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards(CardsShuffler shuffler) {
        this.cards = shuffler.shuffleCards(initializeCards());
    }

    private static List<Card> initializeCards() {
        List<Card> cards = new ArrayList<>();
        for (Value value : Value.values()) {
            for (Shape shape : Shape.values()) {
                cards.add(new Card(value.getValue(), shape.getShape()));
            }
        }
        return cards;
    }

    public boolean contains(final Card card) {
        return cards.contains(card);
    }

    public Card receiveCard(final int index) {
        return cards.get(index);
    }
}
