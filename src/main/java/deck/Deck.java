package deck;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import card.Card;

public class Deck {

    private final Deque<Card> cards;

    public Deck(DeckCreateStrategy createStrategy) {
        this.cards = new ArrayDeque<>(createStrategy.createAllCards());
    }

    public List<Card> draws(final int count) {
        List<Card> cards = new ArrayList<>();
        for (int cnt = 0; cnt < count; cnt++) {
            cards.add(draw());
        }

        return cards;
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 뽑을 카드가 없습니다.");
        }
        return cards.pop();
    }
}
