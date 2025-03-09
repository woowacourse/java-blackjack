package domain.participant;

public class Player extends Participant {
    private Player(final String nickname) {
        super(nickname);
    }

    public static Player from(final String nickname) {
        return new Player(nickname);
    }

    @Override
    public boolean ableToDraw(final int score) {
        return score < BUST_THRESHOLD;
    }

    @Override
    public boolean areYouDealer() {
        return false;
    }
}
