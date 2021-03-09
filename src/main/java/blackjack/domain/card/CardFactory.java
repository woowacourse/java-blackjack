package blackjack.domain.card;

import blackjack.exception.InvalidCardException;

import java.util.*;

public class CardFactory {

    private static final List<Card> cards;

    static {
        cards = create();
    }

    private CardFactory() {
    }

    public static Card of(Type type, Denomination denomination) {
        return cards.stream()
                .filter(card -> card.equals(new Card(type, denomination)))
                .findFirst()
                .orElseThrow(InvalidCardException::new);
    }

    public static List<Card> create() {
        return iterateType(new ArrayList<>());
    }

    public static Deque<Card> createWithShuffle() {
        Collections.shuffle(new ArrayList<>(cards));
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
