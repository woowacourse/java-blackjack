package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardsCache {

    private CardsCache() {}

    private static final List<Card> cards;

    static {
        final List<Card> newCards = new ArrayList<>();
        for (final CardShape shape : CardShape.values()) {
            Arrays.stream(CardNumber.values())
                    .forEach(number -> newCards.add(new Card(shape, number)));
        }
        cards = Collections.unmodifiableList(newCards);
    }

    public static List<Card> getAllCards() {
        return new ArrayList<>(cards);
    }
}
