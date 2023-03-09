package blackjack.domain;

import java.util.List;

public class Dealer extends Person {
    private static final int DEALER_STOP_HIT_BOUND = 17;

    @Override
    public boolean isHit() {
        int totalScore = calculateScore();
        return totalScore < DEALER_STOP_HIT_BOUND;
    }

    public List<Card> getFirstCard() {
        return List.of(cards.get(0));
    }
}
