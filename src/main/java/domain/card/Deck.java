package domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    private static final int INIT_CARDS_SIZE = 2;

    private final Stack<Card> cards;

    private Deck(Stack<Card> cards) {
        this.cards = cards;
    }

    public static Deck create() {
        return Arrays.stream(Type.values())
                .flatMap(type -> createBySymbol(type).stream())
                .collect(Collectors.collectingAndThen(Collectors.toCollection(Stack::new), Deck::new));
    }

    private static List<Card> createBySymbol(Type type) {
        return Arrays.stream(Symbol.values())
                .map(symbol -> new Card(symbol, type))
                .collect(Collectors.toList());
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
