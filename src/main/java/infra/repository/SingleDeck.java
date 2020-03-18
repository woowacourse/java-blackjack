package infra.repository;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class SingleDeck implements Deck {
    static final int SIZE = 52;

    private final List<Card> cards;
    private static final int POP_INDEX = 0;

    private SingleDeck(List<Card> cards) {
        if (cards.size() != SIZE) {
            throw new IllegalArgumentException(String.format("싱글덱의 카드 갯수는 %d개여야 합니다 - 현재 카드 갯수 : %d", SIZE, cards.size()));
        }
        this.cards = cards;
    }

    public static SingleDeck shuffle() {
        Stack<Card> cards = new Stack<>();
        for (Symbol symbol : Symbol.values()) {
            for (Type type : Type.values()) {
                cards.add(new Card(symbol, type));
            }
        }

        Collections.shuffle(cards);

        return new SingleDeck(new ArrayList<>(Collections.unmodifiableCollection(cards)));
    }

    int size() {
        return cards.size();
    }

    @Override
    public Card pop() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("싱글덱이 비어있습니다.");
        }
        return cards.remove(POP_INDEX);
    }
}
