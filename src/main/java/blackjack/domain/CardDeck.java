package blackjack.domain;

import blackjack.domain.human.Players;
import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    public static List<Card> cards;
    static {
        cards = new ArrayList<>();
        for (Denomination value : Denomination.values()) {
//            cards.add(Card.of(value, ))/////
        }
    }

    public static List<Card> get() {
        return List.copyOf(cards);
    }
}
