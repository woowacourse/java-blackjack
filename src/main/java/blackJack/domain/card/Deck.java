package blackJack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Deck {

    private static final LinkedList<Card> deck;

    static {
        deck = Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Denomination.values())
                        .map(denomination -> new Card(suit, denomination)))
                .collect(Collectors.toCollection(LinkedList::new));
        Collections.shuffle(deck);
    }

    public static Card getCard() {
        return deck.pop();
    }
}
