package domain;

import common.Constants;

public class Player extends Participant {

    Player(String name, Hand hand) {
        super(name, hand);
        requireNonDealer(name);
    }

    static Player of(String name) {
        return new Player(name, Hand.empty());
    }

    private void requireNonDealer(String name) {
        if(name.equals("딜러")) {
            throw new IllegalArgumentException("플레이어는 \"딜러\"라는 이름을 사용할 수 없다.");
        }
    }

    @Override
    protected boolean isPlayable() {
        return hand.scoreSum() <= Constants.PLAYER_PLAYING_THRESHOLD;
    }
}
