package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    public static final int FIRST_CARD_INDEX = 0;

    private final List<Card> cards;

    public Deck() {
        cards = initCards();
    }

    private List<Card> initCards() {
        List<Card> cards = new LinkedList<>();
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
        return cards.remove(FIRST_CARD_INDEX);
    }

    public List<Card> initialDraw() {
        List<Card> result = new ArrayList<>();
        result.add(draw());
        result.add(draw());
        return result;
    }

    public int size() {
        return cards.size();
    }
}
