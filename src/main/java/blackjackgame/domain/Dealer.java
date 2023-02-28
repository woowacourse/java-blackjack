package blackjackgame.domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    List<Card> cards;

    public Dealer(Card firstCard, Card secondCard) {
        this.cards = new ArrayList<>(List.of(firstCard, secondCard));
    }

    public int getScore() {
        int totalScore = 0;
        for (Card card : cards) {
            totalScore += card.getScore();
        }
        return totalScore;
    }

    public boolean isPick() {
        return getScore() <= 16;
    }
}
