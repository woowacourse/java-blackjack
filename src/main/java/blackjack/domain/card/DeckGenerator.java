package blackjack.domain.card;

import java.util.*;

public class DeckGenerator {

    public Deque<Card> create() {
        return new ArrayDeque<>(iterateType(new ArrayList<>()));
    }

    public Deque<Card> createWithShuffle() {
        List<Card> cards = new ArrayList<>(create());
        Collections.shuffle(cards);
        return new ArrayDeque<>(cards);
    }

    private List<Card> iterateType(List<Card> cards) {
        for (Type type : Type.values()) {
            iterateDenomination(cards, type);
        }
        return cards;
    }

    private List<Card> iterateDenomination(List<Card> cards, Type type) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(type, denomination));
        }
        return cards;
    }
}
