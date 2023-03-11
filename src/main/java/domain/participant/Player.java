package domain.participant;

import view.ExceptionMessage;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Player extends Participant {

    private final BetAmount betAmount;
    private Player(Name name, BetAmount betAmount) {
        super(new ArrayList<>(), name);
        this.betAmount = betAmount;
    }

    public static Player create(Name name, BetAmount betAmount) {
        if (name.getName().equals(DEALER_NAME)) {
            throw new IllegalArgumentException(ExceptionMessage.NAME_IS_DEALER.getMessage());
        }
        return new Player(name, betAmount);
    }

    @Override
    public int calculatePrize(BigDecimal width) {
        return betAmount.calculatePrize(width);
    }

    @Override
    public boolean shouldHit() {
        throw new UnsupportedOperationException();
    }
}
