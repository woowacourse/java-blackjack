package domain.card;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Deck {

    private final Queue<Card> deck;

    public Deck() {
        LinkedList<Card> cards = createDeck();
        Collections.shuffle(cards);
        deck = cards;
    }

    private LinkedList<Card> createDeck() {
        return Arrays.stream(Denomination.values())
                .flatMap(generateCardForm())
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private Function<Denomination, Stream<? extends Card>> generateCardForm() {
        return denomination -> Arrays.stream(Suit.values())
                .map(suit -> new Card(suit, denomination));
    }

    public Card pickCard() {
        return deck.poll();
    }

    public List<Card> getDeck() {
        return new ArrayList<>(deck);
    }
}
