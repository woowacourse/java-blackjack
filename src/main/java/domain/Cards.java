package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public int getScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getPoint();
        }
        return score;
    }

    public boolean isBlackJack() {
        return (cards.size() == 2) && (getScore() == 21);
    }
}