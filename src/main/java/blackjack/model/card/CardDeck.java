package blackjack.model.card;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CardDeck {
    private final Deque<Card> cards;

    public CardDeck() {
        this.cards = createCards();
    }

    private LinkedList<Card> createCards() {
        final LinkedList<Card> cards = new LinkedList<>();
        for (Symbol symbol : Symbol.values()) {
            pushCardFromSymbol(cards, symbol);
        }
        Collections.shuffle(cards);
        return cards;
    }

    private void pushCardFromSymbol(final List<Card> cards, Symbol symbol) {
        for (Number number : Number.values()) {
            cards.add(new Card(number, symbol));
        }
    }
}
