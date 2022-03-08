package blackJack.domain;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final List<Card> deck;

    public Deck() {
        deck = new ArrayList<>();
        initDeck();
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
}
