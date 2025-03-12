package model.deck;

import java.util.ArrayDeque;
import java.util.Deque;
import model.card.Card;

public class Deck {

    private final Deque<Card> cards;

    public Deck(DeckCreateStrategy createStrategy) {
        this.cards = new ArrayDeque<>(createStrategy.createAllCards());
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 뽑을 카드가 없습니다.");
        }
        return cards.pop();
    }
}
