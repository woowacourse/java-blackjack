package domain;

import java.util.ArrayList;
import java.util.List;

public class PlayerCards {
    private final List<Card> cards;

    public PlayerCards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public void addAll(List<Card> cards) {
        this.cards.addAll(cards);
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
