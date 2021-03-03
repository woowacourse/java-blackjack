package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {

    private CardFactory() {
    }

    public static List<Card> create() {
        return Collections.unmodifiableList(iterateType(new ArrayList<>()));
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
