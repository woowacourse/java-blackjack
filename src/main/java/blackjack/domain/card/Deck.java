package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private static final List<Card> cards = new ArrayList<>();

    static {
        for (Shape shape : Shape.values()) {
            cards.addAll(cardsOf(shape));
        }
    }

    private static List<Card> cardsOf(Shape shape) {
        return Arrays.stream(CardNumber.values())
                .map(number -> new Card(number, shape))
                .collect(Collectors.toList());
    }

    public static Card draw() {
        Collections.shuffle(cards);
        return cards.get(0);
    }

    public static List<Card> getCards() {
        return cards;
    }
}