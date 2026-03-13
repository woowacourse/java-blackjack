package model.paticipant;

import model.judgement.BetAmount;

public class Player extends Participant {

    private static final int PLAYER_HIT_THRESHOLD = 21;

    private final BetAmount betAmount;

    public Player(String name, int betAmount) {
        super(name);
        this.betAmount = new BetAmount(betAmount);
    }

    @Override
    public boolean canHit() {
        return calculateTotalScore() < PLAYER_HIT_THRESHOLD;
    }

    public BetAmount getBetAmount() {
        return betAmount;
    }
}
