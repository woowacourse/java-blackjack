package blackjack.domain.carddeck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CardDeckGenerator {

    private static final List<Card> CACHE_DECK;

    static {
        CACHE_DECK = Arrays.stream(Pattern.values())
                .flatMap(pattern -> Arrays.stream(Number.values()).map(number -> new Card(pattern, number)))
                .collect(Collectors.toList());
    }

    public static List<Card> generate() {
        return new ArrayList<>(CACHE_DECK);
    }
}
