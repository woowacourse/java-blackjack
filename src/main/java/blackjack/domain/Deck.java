package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>(createCards());
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
        // TODO: 자료구조 고민
        Card card = cards.get(0);
        cards.remove(card);

        return card;
    }
}
