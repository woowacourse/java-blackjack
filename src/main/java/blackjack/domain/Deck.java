package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static List<Card> generate() {
        List<Card> cards = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            for (CardValue value : CardValue.values()) {
                Card card = new Card(value, shape);
                cards.add(card);
            }
        }

        return cards;
    }

}
