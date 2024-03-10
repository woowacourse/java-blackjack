package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = initialize();
    }

    private List<Card> initialize() {
        return Arrays.stream(Denomination.values())
                .flatMap(denomination ->
                        Arrays.stream(Symbol.values())
                                .map(symbol -> new Card(denomination, symbol)))
                .collect(Collectors.toList());
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
        cards.remove(cards.size() - 1);
        return card;
    }
}

