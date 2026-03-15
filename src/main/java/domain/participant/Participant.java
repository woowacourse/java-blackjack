package domain.participant;

import domain.card.Card;

public abstract class Participant {
    private static final int BLACKJACK_SATISFYING_HAND_COUNT = 2;
    private static final int BLACKJACK_SATISFYING_SCORE = 21;
    private final Hand hand;

    public Participant(Hand hand) {
        this.hand = hand;
    }

    public abstract void keepCard(Card card);

    public abstract boolean canHit();

    public abstract String getName();

    public int handSize() {
        return hand.getHandSize();
    }

    public Hand getHand() {
        return hand;
    }

    public int getTotalCardScore() {
        return hand.calculateTotalScore();
    }

    public boolean isMaxScore() {
        return getTotalCardScore() == BLACKJACK_SATISFYING_SCORE;
    }

    public boolean isBlackJack() {
        return handSize() == BLACKJACK_SATISFYING_HAND_COUNT && getTotalCardScore() == BLACKJACK_SATISFYING_SCORE;
    }
}
