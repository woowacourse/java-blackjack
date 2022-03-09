package blackJack.domain;

import java.util.Collections;
import java.util.LinkedList;

public class Deck {

    private final LinkedList<Card> deck;

    public Deck() {
        deck = new LinkedList<>();
        initDeck();
        Collections.shuffle(deck);
    }

    private void initDeck() {
        for (Symbol symbol : Symbol.values()) {
            initDeckBySymbol(symbol);
        }
    }

    private void initDeckBySymbol(Symbol symbol) {
        for (Denomination denomination : Denomination.values()) {
            deck.add(new Card(symbol, denomination));
        }
    }

    public Card getCard() {
       return deck.pop();
    }
}
