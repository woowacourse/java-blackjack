package domain.participant;

import static domain.card.Hand.BUST_BOUND;

public class Player extends Participant {

    private final BetAmount betAmount;

    public Player(final Name name, final int betAmount) {
        super(name);
        this.betAmount = new BetAmount(betAmount);
    }

    @Override
    public boolean isDrawable() {
        return !isBust() && !isBlackjack() && (getScore() < BUST_BOUND);
    }


    public int getBetAmount() {
        return betAmount.betAmount();
    }
}
