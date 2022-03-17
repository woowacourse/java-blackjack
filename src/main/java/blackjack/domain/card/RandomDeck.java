package blackjack.domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RandomDeck implements Deck {

    private final LinkedList<Card> cards;

    public RandomDeck() {
        cards = initCards();
    }

    private LinkedList<Card> initCards() {
        LinkedList<Card> cards = new LinkedList<>();
        for (Symbol symbol : Symbol.values()) {
            addDenomination(cards, symbol);
        }
        Collections.shuffle(cards);
        return cards;
    }

    private void addDenomination(List<Card> cards, Symbol symbol) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(symbol, denomination));
        }
    }

    public Card draw() {
        return cards.removeFirst();
    }

    public Cards initialDraw() {
        return new Cards(draw(), draw());
    }

    public int size() {
        return cards.size();
    }
}
