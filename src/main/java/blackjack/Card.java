package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private final Face face;
    private final Suit suit;

    private static final List<Card> cachedCards = new ArrayList<>();

    static {
        for (Suit suit : Suit.values()) {
            for (Face face : Face.values()) {
                cachedCards.add(new Card(suit, face));
            }
        }
    }

    private Card(final Suit suit, final Face face) {
        this.suit = suit;
        this.face = face;
    }

    public static Card of() {
        Card card = cachedCards.get(0);
        cachedCards.remove(0);
        return card;
    }
}
