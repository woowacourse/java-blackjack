package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> deck = new ArrayList<>();

    public Deck() {
        generateCards();
    }

    private void generateCards() {
        for (Symbol symbol: Symbol.values()){
            addCardsWithSymbolOf(symbol);
        }
        Collections.shuffle(this.deck);
    }

    private void addCardsWithSymbolOf(Symbol symbol){
        for (CardNumber cardNumber: CardNumber.values()){
            this.deck.add(new Card(symbol, cardNumber));
        }
    }

    public Card draw() {
        if(this.deck.size() == 0) {
            generateCards();
        }
        return this.deck.remove(0);
    }
}
