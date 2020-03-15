package domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeckFactory {
    private DeckFactory() {
    }

    public static Deck create() {
        return Arrays.stream(Type.values())
                .flatMap(DeckFactory::createBySymbol)
                .collect(Collectors.collectingAndThen(Collectors.toCollection(Stack::new), cards -> {
                    Collections.shuffle(cards);
                    return new Deck(cards);
                }));
    }

    private static Stream<Card> createBySymbol(Type type) {
        return Arrays.stream(Symbol.values())
                .map(symbol -> new Card(symbol, type));
    }
}
