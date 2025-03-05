package domain.participant;

public class Player implements Participant {
    private static final int BUST_THRESHOLD = 21;
    private final String nickname;
    private Player(final String nickname) {
        this.nickname = nickname;
    }

    public static Player from(final String nickname) {
        return new Player(nickname);
    }

    @Override
    public boolean ableToDraw(final int score) {
        return score < BUST_THRESHOLD;
    }
}
