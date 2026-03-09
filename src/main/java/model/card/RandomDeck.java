package model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RandomDeck implements Deck {
    private final Iterator<Card> cards;

    private RandomDeck(List<Card> cards) {
        this.cards = cards.iterator();
    }

    public static RandomDeck of(Suit[] suits, Rank[] ranks) {
        List<Card> cards = new ArrayList<>();

        for (Suit suit : suits) {
            for (Rank rank : ranks) {
                cards.add(Card.of(suit, rank));
            }
        }

        Collections.shuffle(cards);

        return new RandomDeck(List.copyOf(cards));
    }

    @Override
    public Card draw() {
        return cards.next();
    }
}
