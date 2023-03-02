package blackjack.domain.card;

import blackjack.domain.card.exception.NoMoreCardException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {

    private final List<Card> cards;

    private CardDeck(List<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck create() {
        List<Card> cards = new ArrayList<>();
        addShapeSets(cards);
        return new CardDeck(cards);
    }

    private static void addShapeSets(List<Card> cards) {
        for (Shape shape : Shape.values()) {
            addNumberSets(cards, shape);
        }
    }

    private static void addNumberSets(List<Card> cards, Shape shape) {
        for (Number number : Number.values()) {
            cards.add(new Card(shape, number));
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card pick() {
        validateCardExist();
        return cards.remove(0);
    }

    private void validateCardExist() {
        if (cards.isEmpty()) {
            throw new NoMoreCardException();
        }
    }
}
