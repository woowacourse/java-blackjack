package domain.participant;

import domain.blackjack.BetAmount;

public class Player extends Participant {

    private static final int BLACK_JACK_COUNT = 21;
    private final BetAmount betAmount;

    public Player(Name name) {
        super(name);
        this.betAmount = new BetAmount(1000);
    }

    public Player(Name name, BetAmount betAmount) {
        super(name);
        this.betAmount = betAmount;
    }

    @Override
    boolean canHit() {
        return hands.calculateScore() <= BLACK_JACK_COUNT;
    }
}
