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
        for (CardSymbol cardSymbol : CardSymbol.values()) {
            pushCardFromSymbol(cards, cardSymbol);
        }
        Collections.shuffle(cards);
        return cards;
    }

    private void pushCardFromSymbol(final List<Card> cards, CardSymbol cardSymbol) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(cardNumber, cardSymbol));
        }
    }

    public Card draw() {
        return cards.pop();
    }
}
