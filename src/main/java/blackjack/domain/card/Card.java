package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record Card(Suit suit, Denomination denomination) {

    private static final List<Card> CACHE;

    static {
        CACHE = Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Denomination.values())
                        .map(denomination -> new Card(suit, denomination)))
                .toList();
    }

    public static List<Card> values() {
        return new ArrayList<>(CACHE);
    }
}
