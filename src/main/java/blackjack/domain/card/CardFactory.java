package blackjack.domain.card;

import java.util.Stack;

public class CardFactory {

    private static final Stack<Card> CARDS = new Stack<>();

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

    public static Stack<Card> create() {
        return CARDS;
    }
}
