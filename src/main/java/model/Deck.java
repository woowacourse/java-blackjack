package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> deck = new ArrayList<>(52);

    public Deck() {
        initializeDeck();
    }

    public Card getCard() {
        if(deck.isEmpty()) {
            throw new IllegalStateException();
        }
        Collections.shuffle(deck);
        return deck.removeLast();
    }

    private void initializeDeck() {
        for (CardShape shape : CardShape.values()) {
            for (CardNumber number : CardNumber.values()) {
                if(number.equals(CardNumber.ACE_ONE)) {
                    continue;
                }
                deck.add(new Card(number, shape));
            }
        }
    }
}
