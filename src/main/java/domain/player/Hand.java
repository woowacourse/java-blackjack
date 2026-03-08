package domain.player;

import domain.Card;
import domain.CardNumber;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public int calculateScore() {
        int score = 0;
        for (Card card : cards) {
            int point = calculatePoint(card, score);

            score += point;
        }
        return score;
    }

    private int calculatePoint(Card card, int currentScore) {
        if (card.isAce()) {
            return calculateAcePoint(currentScore);
        }
        return card.cardNumber().getPoint();
    }

    private int calculateAcePoint(int currentScore) {
        if (currentScore + CardNumber.ACE.getPoint() > 21) {
            return 1;
        }
        return CardNumber.ACE.getPoint();
    }
}
