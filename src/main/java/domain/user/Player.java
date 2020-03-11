package domain.user;

public class Player extends User {
    public Player(final String name) {
        super(name);
    }

    public boolean isWin(final User opponentUser) {
        return isNotBust() && (opponentUser.isBust() || getScore() >= opponentUser.getScore());
    }
}
