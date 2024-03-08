package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        initialize();
    }

    private void initialize() {
        for (final Denomination denomination : Denomination.values()) {
            Arrays.stream(Symbol.values()).map(symbol -> new Card(denomination, symbol)).forEach(cards::add);
        }
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱이 비어있습니다");
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

