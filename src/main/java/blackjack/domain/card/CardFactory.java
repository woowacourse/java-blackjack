package blackjack.domain.card;

import java.util.Stack;

public class CardFactory {

    private static final Stack<Card> cards = new Stack<>();

    public static Stack<Card> generate() {
        for (Suit suit : Suit.values()) {
            addCardValue(suit);
        }
        return cards;
    }

    private static void addCardValue(Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(Card.create(suit, denomination));
        }
    }
}
