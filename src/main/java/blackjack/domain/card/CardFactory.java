package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardFactory {

    private static final List<Card> CARDS = new ArrayList<>();

    static {
        for (Suit suit : Suit.values()) {
            addCardValue(suit);
        }
    }

    private CardFactory() {

    }

    private static void addCardValue(Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            CARDS.add(Card.create(suit, denomination));
        }
    }

    public static List<Card> create() {
        return new ArrayList<>(CARDS);
    }
}
