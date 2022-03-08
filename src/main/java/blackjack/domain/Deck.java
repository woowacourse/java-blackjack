package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    List<Card> cards = new ArrayList<>();

    public Deck(){
        for (Symbol symbol : Symbol.values()) {
            addDenomination(symbol);
        }
    }

    private void addDenomination(Symbol symbol) {
        for(Denomination denomination : Denomination.values()){
            cards.add(new Card(symbol,denomination));
        }
    }

    public int size() {
        return cards.size();
    }

    public Card draw() {
        Collections.shuffle(cards);
        Card card = cards.get(cards.size() - 1);
        cards.remove(card);
        return card;
    }

    public List<Card> initialDraw(){
        Collections.shuffle(cards);
        List<Card> result = cards.subList(0,2);
        cards = cards.subList(2,size());
        return result;
    }
}
