package blackjack.model.card;

import java.util.Collections;
import java.util.Stack;

public class CardDeck {
    private static final CardDeck deck = new CardDeck();

    private final Stack<Card> cards;

    private CardDeck() {
        this.cards = createCards();
        Collections.shuffle(cards);
    }

    public static CardDeck getInstance() {
        return deck;
    }

    private Stack<Card> createCards() {
        Stack<Card> cards = new Stack<>();
        for (TrumpSymbol symbol : TrumpSymbol.values()) {
            pushCardIn(cards, symbol);
        }
        return cards;
    }

    private void pushCardIn(Stack<Card> cards, TrumpSymbol symbol) {
        for (TrumpNumber number : TrumpNumber.values()) {
            cards.add(new Card(number, symbol));
        }
    }

    public Card draw() {
        return cards.pop();
    }
}
