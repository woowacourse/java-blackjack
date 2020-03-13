package domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Deck {
    public static final int INIT_CARDS_SIZE = 2;

    private Stack<Card> cards;

    private Deck(Stack<Card> cards) {
        this.cards = cards;
    }

    public static Deck create() {
        return Arrays.stream(Type.values())
                .flatMap(Deck::createBySymbol)
                .collect(Collectors.collectingAndThen(Collectors.toCollection(Stack::new), Deck::new));
    }

    private static Stream<Card> createBySymbol(Type type) {
        return Arrays.stream(Symbol.values())
                .map(symbol -> new Card(symbol, type));
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        shuffle();
        return cards.pop();
    }

    public PlayingCards dealInitCards() {
        return IntStream.range(0, INIT_CARDS_SIZE)
                .mapToObj(i -> cards.pop())
                .collect(Collectors.collectingAndThen(Collectors.toList(), PlayingCards::new));
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
