package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class CardDeckGenerator {

    public static CardDeck generate() {
        Stack<Card> cards = createCards();
        Collections.shuffle(cards);
        return CardDeck.generate(cards);
    }

    private static Stack<Card> createCards() {
        Stack<Card> cards = new Stack<>();
        createCardByDenomination(cards);
        return cards;
    }

    private static void createCardByDenomination(Stack<Card> cards) {
        for (Denomination denomination : Denomination.values()) {
            createCardBySymbol(cards, denomination);
        }
    }

    private static void createCardBySymbol(Stack<Card> cards, Denomination denomination) {
        for (Symbol symbol : Symbol.values()) {
            cards.add(Card.of(denomination, symbol));
        }
    }
}
