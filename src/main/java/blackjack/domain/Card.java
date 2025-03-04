package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public record Card(Suit suit, Denomination denomination) {

    private static final List<Card> CACHE = new ArrayList<>();

    static {
        for (Suit suit : Suit.values()) {
            for (Denomination denomination : Denomination.values()) {
                CACHE.add(new Card(suit, denomination));
            }
        }
    }

    public static List<Card> values() {
        return new ArrayList<>(CACHE);
    }
}
