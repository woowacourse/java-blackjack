package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;

import java.util.ArrayDeque;
import java.util.ArrayList;
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
        final List<Card> result = new ArrayList<>();

        for (int i = 0; i < DECK_SIZE; i++) {
            result.addAll(generateOneCardDeck());
        }

        Collections.shuffle(result);
        return new CardDeck(new ArrayDeque<>(result));
    }

    private static List<Card> generateOneCardDeck() { //TODO 인덴트 줄이기
        final List<Card> cards = new ArrayList<>();

        for (Shape shape : Shape.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, shape));
            }
        }

        return cards;
    }

    public Card pop() {
        return cards.pop();
    }

    public int size() {
        return cards.size();
    }
}
