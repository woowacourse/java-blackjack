package blackjack.domain;

public class Dealer extends Person {
    private static final int DEALER_STOP_HIT_BOUND = 17;

    @Override
    public boolean isHitPossible() {
        int totalScore = calculateScore();
        return totalScore < DEALER_STOP_HIT_BOUND;
    }

    public Card getFirstCard() {
        return hand.getAllCards().get(0);
    }
}
