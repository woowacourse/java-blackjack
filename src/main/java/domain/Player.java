package domain;

import common.Constants;

public class Player extends Participant {

    public Player(String name, Hand hand) {
        super(name, hand);
    }

    static Player of(String name, DrawStrategy drawStrategy) {
        return new Player(name, Hand.of(drawStrategy));
    }

    @Override
    protected boolean isPlayable() {
        return hand.scoreSum() <= Constants.PLAYER_PLAYING_THRESHOLD;
    }
}
