package domain;

import common.Constants;

public class Player extends Participant {

    public Player(String name, Hand hand) {
        super(name, hand);
    }

    @Override
    protected boolean isPlayable() {
        return hand.scoreSum() <= Constants.PLAYER_PLAYING_THRESHOLD;
    }
}
