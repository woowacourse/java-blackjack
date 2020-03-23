package infra.repository;

import domain.card.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SingleDeck implements Deck {
    static final int SIZE = 52;
    static final String EMPTY_DECK_MESSAGE = "싱글덱이 비어있습니다.";
    private static final int POP_INDEX = 0;
    private static final List<Card> CARDS_BOX;

    private final List<Card> cards;

    static {
        CARDS_BOX = new ArrayList<>();
        for (Symbol symbol : Symbol.values()) {
            for (Type type : Type.values()) {
                CARDS_BOX.add(new Card(symbol, type));
            }
        }
    }

    private SingleDeck(List<Card> cards) {
        if (cards.size() != SIZE) {
            throw new IllegalArgumentException(String.format("싱글덱의 카드 갯수는 %d개여야 합니다 - 현재 카드 갯수 : %d", SIZE, cards.size()));
        }
        this.cards = cards;
    }

    public static SingleDeck setUp() {
        List<Card> cards = new ArrayList<>(Collections.unmodifiableCollection(CARDS_BOX));
        return new SingleDeck(cards);
    }

    @Override
    public Deck shuffle() {
        Collections.shuffle(cards);
        return new SingleDeck(cards);
    }

    @Override
    public Card pop() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(EMPTY_DECK_MESSAGE);
        }
        return cards.remove(POP_INDEX);
    }

    int size() {
        return cards.size();
    }
}
