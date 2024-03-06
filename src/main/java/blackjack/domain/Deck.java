package blackjack.domain;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        this.cards = new LinkedList<>(createCards());
    }

    private static Set<Card> createCards() {
        // TODO: 리팩토링
        Set<Card> cards = new LinkedHashSet<>();

        for (CardShape cardShape : CardShape.values()) {
            for (CardNumber cardNumber : CardNumber.values()) {
                cards.add(new Card(cardNumber, cardShape));
            }
        }

        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card pick() {
        Card card = cards.get(0);
        cards.remove(card);

        return card;
    }

    public List<Card> pick(final int count) {
        return Stream.generate(this::pick)
                .limit(count)
                .toList();
    }
}
