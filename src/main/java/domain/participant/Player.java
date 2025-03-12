package domain.participant;

import domain.game.GameRule;

public class Player extends Participant {
    private final String nickname;

    public Player(final String nickname) {
        super();
        this.nickname = nickname;
    }

    @Override
    public boolean ableToDraw() {
        return getScore() < GameRule.BUST_THRESHOLD.getValue();
    }

    @Override
    public boolean areYouDealer() {
        return false;
    }

    @Override
    public String getNickname() {
        return nickname;
    }
}
