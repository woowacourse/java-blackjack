package domain.card;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class CardDeck {

    private final Deque<Card> cards;

    private CardDeck(final List<Card> cards) {
        this.cards = new ArrayDeque<>(cards);
    }

    public static CardDeck shuffledFullCardDeck() {
        final List<Card> cards = makeTotalCards();
        Collections.shuffle(cards);
        return new CardDeck(cards);
    }

    private static List<Card> makeTotalCards() {
        return stream(CardShape.values())
                .flatMap(CardDeck::makeCardCorrespondsShape)
                .collect(toList());
    }

    private static Stream<Card> makeCardCorrespondsShape(final CardShape shape) {
        return stream(CardValue.values())
                .map(value -> new Card(shape, value));
    }

    public List<Card> cards() {
        return new ArrayList<>(cards);
    }

    public Card draw() {
        return this.cards.pollFirst();
    }
}
