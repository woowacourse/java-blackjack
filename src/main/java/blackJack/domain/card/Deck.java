package blackJack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Deck {

    private static final LinkedList<Card> deck;

    static {
        deck = Arrays.stream(Symbol.values())
                .flatMap(symbol -> Arrays.stream(Denomination.values())
                        .map(denomination -> new Card(symbol, denomination)))
                .collect(Collectors.toCollection(LinkedList::new));
        Collections.shuffle(deck);
    }

    public Card getCard() {
        return deck.pop();
    }
}
