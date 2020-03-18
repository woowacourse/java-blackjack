package model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardFactory {

    public static List<Card> createCardList() {
        List<Card> cards = Arrays
                .stream(Type.values())
                .flatMap(CardFactory::mapToCard)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(cards);
    }

    private static Stream<Card> mapToCard(Type type) {
        return Arrays
                .stream(Symbol.values())
                .map(symbol -> new Card(symbol, type));
    }
}
