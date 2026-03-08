package model.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import model.Card;
import model.Rank;
import model.Suit;

public class DeckImpl implements Deck {
    private final Iterator<Card> cards;

    private DeckImpl(List<Card> cards) {
        this.cards = cards.iterator();
    }

    public static DeckImpl of(List<Suit> suits, List<Rank> ranks) {
        List<Card> cards = new ArrayList<>();

        for (Suit suit : suits) {
            for (Rank rank : ranks) {
                cards.add(Card.of(suit, rank));
            }
        }

        Collections.shuffle(cards);

        return new DeckImpl(List.copyOf(cards));
    }

    @Override
    public Card draw() {
        return cards.next();
    }
}
