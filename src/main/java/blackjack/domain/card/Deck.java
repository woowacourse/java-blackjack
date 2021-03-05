package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private static final List<Card> cards = new ArrayList<>();

    static {
        for (Suit suit : Suit.values()) {
            cards.addAll(cardsOf(suit));
        }
    }

    private static List<Card> cardsOf(Suit suit) {
        return Arrays.stream(Number.values())
                     .map(number -> new Card(number, suit))
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
