package domain.participant;

import domain.Hand;


public class Player extends Participant {
    public static final int MAX_PLAYER_CAN_RECEIVE_SCORE = 21;

    public Player(String name, Hand hand) {
        super(name, hand);
    }

    @Override
    public boolean canReceiveCard() {
        return hand.calculateScore() < MAX_PLAYER_CAN_RECEIVE_SCORE;
    }
}
