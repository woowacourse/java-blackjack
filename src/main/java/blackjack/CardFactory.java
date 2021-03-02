package blackjack;

import java.util.Stack;

public class CardFactory {

    private static final Stack<Card> cards = new Stack<>();

    public static Stack<Card> generate() {
        for (CardSymbol cardSymbol : CardSymbol.values()) {
            addCardValue(cardSymbol);
        }
        return cards;
    }

    private static void addCardValue(CardSymbol cardSymbol) {
        for (CardValue cardValue : CardValue.values()) {
            cards.add(Card.create(cardSymbol, cardValue));
        }
    }
}
