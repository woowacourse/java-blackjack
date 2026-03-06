package model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Cards {

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards createDeck() {
        List<Card> cards = Arrays.stream(CardShape.values())
                .flatMap(Cards::combinate)
                .toList();
        return new Cards(cards);
    }

    private static Stream<Card> combinate(CardShape shape) {
        return Arrays.stream(CardValue.values())
                .map(value -> new Card(shape, value));
    }

    public int size() {
        return cards.size();
    }
}
