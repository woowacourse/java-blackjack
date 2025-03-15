package participant;

import game.GameRule;

public class Player extends Participant {
    private final String nickname;

    public Player(final String nickname) {
        super();
        this.nickname = nickname;
    }

    @Override
    public boolean ableToDraw() {
        return getScore() < GameRule.BLACK_JACK.getValue();
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public String getNickname() {
        return nickname;
    }
}
