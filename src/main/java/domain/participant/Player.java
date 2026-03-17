package domain.participant;

import java.math.BigDecimal;

import static domain.game.BlackjackRule.BLACK_JACK;

public class Player extends Participant {
    private BetAmount betAmount;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canDraw() {
        return super.score() < BLACK_JACK;
    }

    public void bet(BigDecimal betAmount) {
        this.betAmount = new BetAmount(betAmount);
    }

    public BigDecimal betAmount() {
        return betAmount.amount();
    }
}
