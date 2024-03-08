package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        initialize();
    }

    private void initialize() {
        for (final Denomination denomination : Denomination.values()) {
            for (final Symbol symbol : Symbol.values()) {
                cards.add(new Card(denomination, symbol));
            }
        }
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException();
        }
        Collections.shuffle(cards);
        return pollLastCard();
    }

    private Card pollLastCard() {
        final Card card = cards.get(cards.size() - 1);
        cards.remove(cards.size()-1);
        return card;
    }
}

