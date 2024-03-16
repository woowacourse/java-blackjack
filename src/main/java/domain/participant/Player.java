package domain.participant;

import domain.Bet.BetAmount;
import domain.blackjack.WinStatus;

public class Player extends Participant {

    private static final int BLACK_JACK_COUNT = 21;

    private final BetAmount betAmount;

    public Player(Name name, BetAmount betAmount) {
        super(name);
        this.betAmount = betAmount;
    }

    public Player(String name, double betAmount) {
        this(new Name(name), BetAmount.from(betAmount));
    }

    public double profit(WinStatus winStatus) {
        return betAmount.getValue() * (winStatus.getProfitMultiplier());
    }

    @Override
    public boolean canHit() {
        return hands.calculateScore() <= BLACK_JACK_COUNT;
    }


}
