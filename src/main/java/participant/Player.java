package participant;

public class Player extends Participant {
    private static final int ABLE_TO_DRAW = 22;
    private final String nickname;

    public Player(final String nickname) {
        super();
        this.nickname = nickname;
    }

    @Override
    public boolean ableToDraw() {
        return getScore() < ABLE_TO_DRAW;
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
