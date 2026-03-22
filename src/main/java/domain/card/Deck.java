package domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck {
    private final Deque<Card> cards;

    public Deck() {
        List<Card> allCards = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                allCards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(allCards);
        this.cards = new ArrayDeque<>(allCards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱에 카드가 없습니다.");
        }
        return cards.pop();
    }

}
