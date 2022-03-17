package blackJack.domain.Card;

import java.util.LinkedList;

public class CardFactory {

    public static final LinkedList<Card> CARD_CACHE = new LinkedList<>();

    static {
        for (Suit suit : Suit.values()) {
            initCards(CARD_CACHE, suit);
        }
    }

    private static void initCards(LinkedList<Card> cards, Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(suit, denomination));
        }
    }
}
