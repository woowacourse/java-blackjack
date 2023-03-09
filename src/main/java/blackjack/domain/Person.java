package blackjack.domain;

import java.util.List;

abstract class Person {
    private static final int ACE_BONUS_SCORE = 10;
    private static final int BURST_SCORE = 21;

    protected Hand hand;

    Person() {
        this.hand = new Hand();
    }

    void addCard(Card card) {
        hand.add(card);
    }

    int calculateScore() {
        int totalScore = hand.getTotalScore();
        if (totalScore > BURST_SCORE && hand.hasACE()) {
            return totalScore - ACE_BONUS_SCORE;
        }
        return totalScore;
    }

    abstract boolean isHit();

    List<Card> getAllCards() {
        return hand.getAllCards();
    }
}
