package domain.participant;

import java.math.BigDecimal;

import static domain.BlackjackRule.BLACK_JACK;

public class Player extends Participant {
    private BigDecimal betAmount = BigDecimal.ZERO;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canDraw() {
        return super.score() <= BLACK_JACK;
    }

    public void bet(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    public BigDecimal betAmount() {
        return betAmount;
    }
}
