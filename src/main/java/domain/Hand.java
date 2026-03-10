package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private final List<Card> cards;
    private Score score;
    private int aceCount;

    public Hand() {
        this.cards = new ArrayList<>();
        this.score = new Score();
        this.aceCount = 0;
    }

    public void addCard(Card card) {
        this.cards.add(card);
        score = score.addScore(card.getScore());
        if (card.isAce()) {
            aceCount++;
        }
    }

    public Score getScore() {
        return score;
    }

    public int getResult() {
        return score.getScore();
    }

    public boolean checkBust() {
        while (score.getScore() > 21 && aceCount > 0) {
            score = score.subScore(10);
            aceCount--;
        }
        return score.isBust();
    }

    public int size() {
        return cards.size();
    }

    public Card get(int index) {
        return cards.get(index);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
