package domain.participant;

import domain.Bet;
import domain.Hand;


public class Player extends Participant {
    private static final int MAX_PLAYER_CAN_RECEIVE_SCORE = 21;

    private final Bet bet;

    public Player(String name, Hand hand, String betAmount) {
        super(name, hand);
        this.bet = new Bet(betAmount);
    }

    public int getBetAmount() {
        return bet.getAmount();
    }

    @Override
    public boolean canReceiveCard() {
        return getScore() < MAX_PLAYER_CAN_RECEIVE_SCORE;
    }
}
