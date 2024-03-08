package domain;

import java.util.*;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        initialize();
    }
    private void initialize() {
        for (Denomination denomination : Denomination.values()) {
            for (Symbol symbol : Symbol.values()) {
                cards.add(new Card(denomination, symbol));
            }
        }
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException();
        }
        Collections.shuffle(cards);
        return pollLastCard();
    }

    private Card pollLastCard() {
        Card card = cards.get(cards.size() - 1);
        cards.remove(cards.size()-1);
        return card;
    }
}

