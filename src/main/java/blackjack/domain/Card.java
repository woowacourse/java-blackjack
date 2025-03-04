package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public record Card(String suit, String denomination) {

    private static final List<Card> CACHE = new ArrayList();

    static {
        for (Suit suit : Suit.values()) {
            for (Denomination denomination : Denomination.values()) {
                CACHE.add(new Card(suit.name(), denomination.name()));
            }
        }
    }

    public static List<Card> values() {
        return CACHE;
    }
}
