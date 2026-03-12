package domain.participant;

import domain.Bet;
import domain.Hand;


public class Player extends Participant {
    public static final int MAX_PLAYER_CAN_RECEIVE_SCORE = 21;

    private Bet bet;

    public Player(String name, Hand hand, String betAmount) {
        super(name, hand);
        this.bet = new Bet(betAmount);
    }

    @Override
    public boolean canReceiveCard() {
        return hand.calculateScore() < MAX_PLAYER_CAN_RECEIVE_SCORE;
    }
}
