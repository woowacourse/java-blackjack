package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck() {
        for (Symbol symbol : Symbol.values()) {
            addDenomination(symbol);
        }
    }

    private void addDenomination(Symbol symbol) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(symbol, denomination));
        }
    }

    public int size() {
        return cards.size();
    }

    public Card draw() {
        Collections.shuffle(cards);
        return cards.remove(cards.size() - 1);
    }

    public List<Card> initialDraw() {
        Collections.shuffle(cards);
        List<Card> result = cards.subList(0, 2);
        cards = cards.subList(2, size());
        return result;
    }
}
