package domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
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

    public CardArea createCardArea() {
        return CardArea.initialWithTwoCard(draw(), draw());
    }

    public Card draw() {
        return this.cards.pollFirst();
    }

    public List<Card> cards() {
        return new ArrayList<>(cards);
    }
}
