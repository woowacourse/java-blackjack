package model.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import model.card.Card;
import model.card.CardShape;
import model.card.CardValue;

public class CardFactory {

    private static final List<Card> FULL_CARDS = createInitCards();

    private static List<Card> createInitCards() {
        return Arrays.stream(CardShape.values())
                .flatMap(CardFactory::combinate)
                .toList();
    }

    private static Stream<Card> combinate(CardShape shape) {
        return Arrays.stream(CardValue.values())
                .map(value -> new Card(shape, value));
    }

    public static List<Card> createFullCards() {
        return new ArrayList<>(FULL_CARDS);
    }
}
