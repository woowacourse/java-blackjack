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

    public boolean downgradeAce() {
        // TODO depth 줄이기
        for (Card card : hands) {
            if (card.getValue() == Value.ACEHIGH) {
                card.downgradeAce();
                handsScore.addScore(-10);
                return true;
            }
        }
        return false;
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
