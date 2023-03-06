package domain.card;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {

    private static final Queue<Card> deck;

    static {
        LinkedList<Card> cards = createDeck();
        Collections.shuffle(cards);
        deck = cards;
    }

    private static LinkedList<Card> createDeck() {
        return Arrays.stream(Denomination.values())
                .flatMap(generateCardForm())
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private static Function<Denomination, Stream<? extends Card>> generateCardForm() {
        return denomination -> Arrays.stream(Suit.values())
                .map(suit -> new Card(suit, denomination));
    }

    public static Card pickCard() {
        return deck.poll();
    }

    public static List<Card> getDeck() {
        return new ArrayList<>(deck);
    }
}
