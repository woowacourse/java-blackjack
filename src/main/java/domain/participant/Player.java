package domain.participant;

import java.math.BigDecimal;

import static domain.game.BlackjackRule.BLACK_JACK;

public class Player extends Participant {
    private final BetAmount betAmount;

    public Player(String name, BetAmount betAmount) {
        super(name);
        this.betAmount = betAmount;
    }

    @Override
    public boolean canDraw() {
        return super.score() < BLACK_JACK;
    }

    public BigDecimal betAmount() {
        return betAmount.amount();
    }
}
