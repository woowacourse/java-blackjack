package model;

import java.util.ArrayDeque;

public class Deck implements DeckStrategy {

    private final ArrayDeque<Card> cards;

    public Deck(DeckCreateStrategy createStrategy) {
        this.cards = new ArrayDeque<>(createStrategy.createAllCards());
    }

    @Override
    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("뽑을 카드 없음");
        }
        return cards.pop();
    }
}
