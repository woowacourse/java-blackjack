package domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CardsFactory {
    private static final List<Card> cards;

    static {
        cards = Arrays.stream(CardSuit.values())
                .flatMap(suit -> Arrays.stream(CardNumber.values())
                        .map(number -> new Card(suit, number)))
                .collect(Collectors.toList());
    }

    public static List<Card> getCards() {
        return cards;
    }
}
