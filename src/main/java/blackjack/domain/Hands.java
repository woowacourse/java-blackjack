package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Hands {
    private final List<Card> hands;
    private final HandsScore handsScore;

    public Hands() {
        this.hands = new ArrayList<>();
        this.handsScore = new HandsScore();
    }

    public void addCard(Card card) {
        handsScore.addScore(card.getScore());
        hands.add(card);
    }

    public boolean isBurst() {
        return handsScore.isBurst();
    }

    public int getHandsScore() {
        return handsScore.getScore();
    }

    public List<Card> getHands() {
        return hands;
    }
}
