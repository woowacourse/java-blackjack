package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class CardDeck {
    private static final int DECK_SIZE = 6;

    private final Deque<Card> cards;

    public CardDeck(final Deque<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck generate() {
        final List<Card> deck = new ArrayList<>();

        for (int i = 0; i < DECK_SIZE; i++) {
            deck.addAll(generateOneCardDeck());
        }

        Collections.shuffle(deck);
        return new CardDeck(new ArrayDeque<>(deck));
    }

    public Card pop() {
        return cards.pop();
    }

    public int size() {
        return cards.size();
    }

    private static List<Card> generateOneCardDeck() {
        return Arrays.stream(Shape.values())
                .flatMap(shape -> matching(shape).stream())
                .toList();
    }

    private static List<Card> matching(final Shape shape) {
        return Arrays.stream(Rank.values())
                .map(rank -> new Card(rank, shape))
                .toList();
    }

}
