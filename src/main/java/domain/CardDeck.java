package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private final List<Card> cards;

    public CardDeck() {
        this.cards = new ArrayList<>();
        initializeDeck();
        shuffle();
    }

    private void initializeDeck() {
        for (Shape shape : Shape.values()) {
            Arrays.stream(Value.values())
                    .forEach(value -> cards.add(new Card(shape, value)));
        }
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public Card pick() {
        return cards.remove(cards.size() - 1);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int getSize() {
        return cards.size();
    }

}
