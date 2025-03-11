package blackjack.model.game;

import blackjack.model.card.Card;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Deck {
    private final Deque<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = new ArrayDeque<>(cards);
    }


    public int getCardCount() {
        return cards.size();
    }

    public Card drawCard() {
        return cards.pollLast();
    }
}
