package model.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import model.card.Card;
import model.card.CardShape;
import model.card.CardValue;

public class CardFactory {

    private final List<Card> fullCards;

    public CardFactory() {
        this.fullCards = Arrays.stream(CardShape.values())
                .flatMap(CardFactory::combinate)
                .toList();
    }

    private static Stream<Card> combinate(CardShape shape) {
        return Arrays.stream(CardValue.values())
                .map(value -> new Card(shape, value));
    }

    public List<Card> createFullCards() {
        return new ArrayList<>(fullCards);
    }
}
