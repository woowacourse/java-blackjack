package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Deck {
    private static final List<Card> cards = new ArrayList<>();

    static {
        List<Card> createdCards = Arrays.stream(CardNumber.values())
                .flatMap(number -> Arrays.stream(Shape.values()).map(shape -> new Card(number, shape)))
                .collect(toList());
        cards.addAll(createdCards);
    }

    public static Card draw() {
        Collections.shuffle(cards);
        return cards.get(0);
    }
}