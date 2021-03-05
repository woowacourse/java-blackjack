package blackjack.domain.carddeck;

import java.util.ArrayList;
import java.util.List;

public class CardDeckGenerator {

    private static final List<Card> CACHE_DECK = new ArrayList<>();

    static {
        for (Pattern pattern : Pattern.values()) {
            addNumbersByPattern(pattern);
        }
    }

    private static void addNumbersByPattern(Pattern pattern) {
        for (Number number : Number.values()) {
            CACHE_DECK.add(new Card(pattern, number));
        }
    }

    public static List<Card> generate() {
        return new ArrayList<>(CACHE_DECK);
    }

}
