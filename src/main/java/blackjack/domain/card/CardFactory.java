package blackjack.domain.card;

import java.util.*;

public class CardFactory {

    private CardFactory() {
    }

    public static Deque<Card> create() {
        List<Card> cards = iterateType(new ArrayList<>());
        return new ArrayDeque<>(cards);
    }

    public static Deque<Card> createWithShuffle() {
        List<Card> cards = iterateType(new ArrayList<>());
        Collections.shuffle(cards);
        return new ArrayDeque<>(cards);
    }

    private static List<Card> iterateType(List<Card> cards) {
        for (Type type : Type.values()) {
            iterateDenomination(cards, type);
        }
        return cards;
    }

    private static List<Card> iterateDenomination(List<Card> cards, Type type) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(type, denomination));
        }
        return cards;
    }
}
