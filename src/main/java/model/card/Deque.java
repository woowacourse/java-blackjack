package model.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Deque {

    private final List<Card> cards;

    public Deque() {
        cards = initCards();
    }

    private List<Card> initCards() {
        return Arrays.stream(CardNumber.values())
            .flatMap(this::createCards)
            .toList();
    }

    private Stream<Card> createCards(CardNumber cardNumber) {
        return Arrays.stream(CardShape.values())
            .map(cardShape -> new Card(cardNumber, cardShape));
    }

    public List<Card> getCards() {
        return cards;
    }
}
