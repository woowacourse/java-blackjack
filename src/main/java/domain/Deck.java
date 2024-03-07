package domain;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Deck {
    private final Set<Card> cards = new LinkedHashSet<>();

    public Card draw() {
        int currentSize = cards.size();
        while (currentSize == cards.size()) {
            cards.add(Card.makeRandomCard(new RandomNumberGeneartor(0, 13)));
        }
        return lastElement();
    }

    private Card lastElement() {
        return cards.stream().skip(cards.size() - 1).findAny().orElseThrow();
    }
}

