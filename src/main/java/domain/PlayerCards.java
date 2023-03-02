package domain;

import java.util.ArrayList;
import java.util.List;

public class PlayerCards {
    private final List<Card> cards;

    public PlayerCards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int getScore() {
        int score = 0;

        for (Card card : cards) {
            CardNumber cardNumber = card.getNumber();
            score += cardNumber.getScore();
        }
        return score;
    }

    public List<Card> toList() {
        return List.copyOf(cards);
    }

    public Card getFirst() {
        return cards.get(0);
    }
}
