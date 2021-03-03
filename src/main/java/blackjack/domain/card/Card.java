package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Card {
    private final Face face;
    private final Suit suit;

    private static final List<Card> cached = new ArrayList<>();

    static {
        for (Suit suit : Suit.values()) {
            for (Face face : Face.values()) {
                cached.add(new Card(suit, face));
            }
        }
        Collections.shuffle(cached);
    }

    public Card(final Suit suit, final Face face) {
        //private vs public
        this.suit = suit;
        this.face = face;
    }

    public static Card of() {
        Card card = cached.get(0);
        cached.remove(0);
        return card;
    }

    public boolean isAce() {
        return this.face.equals(Face.ACE);
    }

    public int getFaceValue(){
        return this.face.getValue();
    }
}
