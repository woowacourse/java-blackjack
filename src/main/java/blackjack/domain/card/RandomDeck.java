package blackjack.domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RandomDeck implements Deck {
    public static final int FIRST_CARD_INDEX = 0;

    private final List<Card> cards;

    public RandomDeck() {
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

    public Cards initialDraw() {
        return new Cards(draw(), draw());
    }

    public int size() {
        return cards.size();
    }
}
