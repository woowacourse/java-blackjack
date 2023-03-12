package blackjack.domain;

import java.util.List;

abstract class Person {
    private static final int ACE_BONUS_SCORE = 10;
    private static final int BURST_SCORE = 21;

    protected Hand hand;

    Person() {
        this.hand = new Hand();
    }

    abstract boolean isHitPossible();

    void addCard(Card card) {
        hand.add(card);
    }

    public int calculateScore() {
        int totalScore = hand.getTotalScore();
        if (totalScore > BURST_SCORE && hand.hasACE()) {
            return totalScore - ACE_BONUS_SCORE;
        }
        return totalScore;
    }

    public List<Card> getAllCards() {
        return hand.getAllCards();
    }
}
