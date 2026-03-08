package domain;

import common.Constants;

public class Player extends Participant {

    Player(String name, Hand hand) {
        super(name, hand);
    }

    static Player of(String name) {
        return new Player(name, Hand.empty());
    }

    @Override
    protected boolean isPlayable() {
        return hand.scoreSum() <= Constants.PLAYER_PLAYING_THRESHOLD;
    }
}
