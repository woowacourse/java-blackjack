package domain.participant;

import domain.BetAmount;
import domain.ExceptionCode;

import java.util.ArrayList;

public class Player extends Participant {

    private final BetAmount betAmount;
    private Player(Name name, BetAmount betAmount) {
        super(new ArrayList<>(), name);
        this.betAmount = betAmount;
    }

    public static Player create(Name name, BetAmount betAmount) {
        if (name.getName().equals(DEALER_NAME)) {
            throw new IllegalArgumentException(ExceptionCode.PLAYER_INVALID_NAME.getExceptionCode());
        }
        return new Player(name, betAmount);
    }

    @Override
    public boolean shouldHit() {
        throw new UnsupportedOperationException();
    }
}
