package domain.participant;

import domain.participant.betting.BetAmount;

public class Player extends Participant {
    private final BetAmount betAmount;

    public Player(String name, int betAmount) {
        super(name);
        this.betAmount = new BetAmount(betAmount);
    }

    @Override
    public boolean isNotFinished() {
        return !isBust() && !isBlackjack();
    }

    public int getBetAmount() {
        return betAmount.getValue();
    }
}
