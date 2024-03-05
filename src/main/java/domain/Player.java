package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<Card> cards = new ArrayList<>();


    public void addCard(final Card card) {
        cards.add(card);
    }


    public int calculateScore() {
        int score = 0;
        for (final Card card : cards) {
            score += card.getValue(score);
        }
        return score;
    }
}
